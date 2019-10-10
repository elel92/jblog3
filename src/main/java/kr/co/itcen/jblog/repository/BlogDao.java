package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDao {
	@Autowired
	SqlSession sqlSession;
	
	public Map<String, Object> getAll(String id, int categoryNo, int postNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> input_map = new HashMap<String, Object>();
		input_map.put("id", id);
		input_map.put("categoryNo", categoryNo);
		input_map.put("postNo", postNo);
		
		map.put("blog", sqlSession.selectOne("blog.select_title", id));
		//map.put("blog_logo", sqlSession.selectOne("blog.select_logo", id));
		
		System.out.println("카테:" + categoryNo);
		System.out.println("포스트:" +postNo);
		
		map.put("category", sqlSession.selectList("blog.select_category", id));
		map.put("post", sqlSession.selectList("blog.select_post_all", input_map));
		
		
		return map;
	}

}
