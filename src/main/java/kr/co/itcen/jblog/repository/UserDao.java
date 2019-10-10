package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;

	public void insert(UserVo userVo) {
		sqlSession.insert("user.insertUser", userVo);
		
		insertBlog(userVo.getId());
		insertCategory(userVo.getId());
	}
	
	public void insertBlog(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("title", "님의 블로그입니다.");
		//map.put("logo", userVo.getId());
		
		sqlSession.insert("user.insertBlog", map);
	}
	
	public void insertCategory(String id) {
		String reg_date = sqlSession.selectOne("selectRegDate", id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "기타");
		map.put("description", "카테고리를 지정하지 않음");
		map.put("reg_date", reg_date);
		map.put("id", id);
		
		sqlSession.insert("user.insertCategory", map);
	}

	public UserVo get(UserVo userVo) {
		UserVo vo = sqlSession.selectOne("user.selectUser", userVo);
		
		return vo;
	}
}
