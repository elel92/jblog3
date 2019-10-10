package kr.co.itcen.jblog.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.jblog.service.BlogService;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String main(@PathVariable String id, @PathVariable Optional<Integer> pathNo1, @PathVariable Optional<Integer> pathNo2, ModelMap modelMap) {
		System.out.println("아이디" + id);
		System.out.println(pathNo1);
		System.out.println(pathNo2);
		
		int categoryNo = 0;
		int postNo = 0;
		
		if(pathNo2.isPresent()) {
			postNo = pathNo2.get();
			categoryNo = pathNo1.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		modelMap.putAll(blogService.getAll(id, categoryNo, postNo));
		
		modelMap.addAttribute("postNo", postNo);
		
		return "blog/blog-main";
	}
	
//	@ResponseBody
//	@RequestMapping("/admin/basic")
//	public String adminBasic(@PathVariable String id) {
//		System.out.println(id);
//		return "blog/blog-admin-basic";
//	}
//	
	
}
