package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Auth
@Controller
@RequestMapping("/blogAdmin")
public class BlogAdminController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileuploadService fileuploadService;
	

	
	
	@RequestMapping("/blog-admin-basic")
	public String adminBasic(Model model,@AuthUser UserVo authUser) {
		model.addAttribute("blogVo",blogService.getBlog(authUser.getNo()));
		return "blog/blog-admin-basic";
	}

	@RequestMapping("/blog-modify")
	public String blogModify(@RequestParam(value="logo-file") MultipartFile multipartFile,@ModelAttribute BlogVo blogVo,@AuthUser UserVo authuser
			,Model model) {
		String logo="";
		if(multipartFile!=null)
		 logo = fileuploadService.restore(multipartFile,authuser.getNo());
		blogVo.setUserNo(authuser.getNo());
		blogVo.setLogo(logo);
		model.addAttribute("result",blogService.blogModify(blogVo));
		return "blog/blog-admin-basic";
	}
	
	
	
	
}
