package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;
import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class AdminDao {
	@Autowired
	SqlSession sqlSession;

	public BlogVo getBlog(UserVo userVo) {
		BlogVo vo = sqlSession.selectOne("admin.getBlog", userVo);
		
		return vo;
	}

	public void updateBasic(BlogVo vo) {
		sqlSession.update("admin.updateBasic", vo);
	}

	public List<CategoryVo> getCategory(UserVo userVo) {
		List<CategoryVo> list = sqlSession.selectList("admin.selectCategory", userVo);
		
		return list;
	}

	public void insertPost(PostVo postVo) {
		sqlSession.update("admin.insertPost", postVo);
	}

	public List<CategoryVo> getCateList(String id) {
		List<CategoryVo> list = sqlSession.selectList("admin.selectCateList", id);
		
		return list;
	}

	public void deleteCateList(String id, String cate_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("cate_no", cate_no);
		
		sqlSession.delete("deletePostList", map);
		sqlSession.delete("deleteCateList", map);
	}
}
