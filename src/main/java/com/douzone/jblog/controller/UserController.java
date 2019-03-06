package com.douzone.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.dto.JSONResult;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/join",method=RequestMethod.GET)
	String join() {
		
		return "user/join";
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	String login() {
		
		return "user/login";
	}
	@RequestMapping(value="/join",method=RequestMethod.POST)
	String join(@ModelAttribute @Valid UserVo userVo,BindingResult result,Model model) {
			
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		
		if(userService.join(userVo))
			return "user/joinsuccess";
		else
		{
			System.out.println("가입실패");
			return "user/join";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/checkId",method=RequestMethod.GET)
	JSONResult checkedId(@RequestParam(value="id",required=false) String id) {
		return JSONResult.success(userService.userSearch(id));
	}
	
	
	
	
}
