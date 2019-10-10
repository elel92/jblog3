package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.AdminDao;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class AdminService {
	@Autowired
	AdminDao adminDao;
	
	public BlogVo getBlog(UserVo userVo) {
		BlogVo blogVo = new BlogVo();
		
		blogVo = adminDao.getBlog(userVo);
		
		return blogVo;
	}

	public void updateBasic(BlogVo vo) {
		adminDao.updateBasic(vo);
	}

	public List<CategoryVo> getCategory(UserVo userVo) {
		List<CategoryVo> list = adminDao.getCategory(userVo);

		return list;
	}

	public void insertPost(PostVo postVo) {
		adminDao.insertPost(postVo);
	}

	public List<CategoryVo> getCateList(String id) {
		List<CategoryVo> list = adminDao.getCateList(id);
		return list;
	}

	public void deleteCate(String id, String cate_no) {
		adminDao.deleteCateList(id, cate_no);
	}
	
}
