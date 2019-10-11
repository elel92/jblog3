package kr.co.itcen.jblog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.service.AdminService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class AdminController {
	@Autowired
	AdminService adminService;
	
	
	@RequestMapping("admin/basic")
	public String adminbasic(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		if(session == null || session.getAttribute("authUser") == null) {
			return "redirect:/";
		}
		
		BlogVo blogVo = adminService.getBlog(userVo);
		
		model.addAttribute("blog", blogVo);
		
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="admin/updateBasic", method=RequestMethod.POST)
	public String updateBasic(@ModelAttribute BlogVo vo, @RequestParam(value="logo-file", required=false) MultipartFile multipartFile, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if(session == null || session.getAttribute("authUser") == null) {
			return "redirect:/";
		}
		
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		adminService.updateBasic(vo, multipartFile);
		
		return "redirect:/" + userVo.getId();
	}
	
	@RequestMapping(value="admin/write", method=RequestMethod.GET)
	public String write(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		if(session == null || session.getAttribute("authUser") == null) {
			return "redirect:/";
		}
		
		BlogVo blogVo = adminService.getBlog(userVo);
		List<CategoryVo> cate_list = adminService.getCategory(userVo);
		System.out.println(cate_list);
		model.addAttribute("cate_list", cate_list);
		model.addAttribute("blog", blogVo);
		
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value="admin/write", method=RequestMethod.POST)
	public String write(@ModelAttribute PostVo postVo, @RequestParam("category") int category_num ,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		postVo.setCategory_no(category_num);
		
		adminService.insertPost(postVo);
		
		return "redirect:/" + userVo.getId();
	}
	
	@RequestMapping(value="admin/category", method=RequestMethod.GET)
	public String category(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		if(session == null || session.getAttribute("authUser") == null) {
			return "redirect:/";
		}
		
		BlogVo blogVo = adminService.getBlog(userVo);
		
		model.addAttribute("blog", blogVo);
		
		List<CategoryVo> cate_list = adminService.getCateList(userVo.getId());
		
		model.addAttribute("cate_list", cate_list);

		return "blog/blog-admin-category";
	}
	
	@ResponseBody
	@RequestMapping("admin/delete")
	public String delete(@PathVariable String id, @RequestParam(value="cate_no", required=false) String cate_no) {
		adminService.deleteCate(id, cate_no);
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("admin/insert")
	public List<CategoryVo> insert(@PathVariable String id, @RequestParam(value="cate_name", required=false) String cate_name, @RequestParam(value="cate_desc", required=false) String cate_desc, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		adminService.insertCate(id, cate_name, cate_desc);
		List<CategoryVo> cate_list = adminService.getCategory(userVo);
		System.out.println(cate_list);
		
		return cate_list;
	}
}





