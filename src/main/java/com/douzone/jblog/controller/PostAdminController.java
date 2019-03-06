package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Auth
@Controller
@RequestMapping("/postAdmin")
public class PostAdminController {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping("/blog-admin-write")
	public String adminWrite(@AuthUser UserVo authUser,Model model) {
		model.addAttribute("list",postService.getCategoryList(authUser.getNo()));
		return "blog/blog-admin-write";
	}
	
	
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write(@ModelAttribute PostVo postVo,@ModelAttribute CategoryVo categoryVo,@AuthUser UserVo authUser) {
		categoryVo.setUserNo(authUser.getNo());
		postService.postWrite(categoryVo, postVo);
		return "redirect:/postAdmin/blog-admin-write";
	}

}
