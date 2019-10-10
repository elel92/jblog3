package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import kr.co.itcen.jblog.repository.BlogDao;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

	public ModelMap getAll(String id, int categoryNo, int postNo) {
		ModelMap modelMap = new ModelMap();
		
		modelMap.addAllAttributes(blogDao.getAll(id, categoryNo, postNo));
		
		return modelMap;
	}
}
