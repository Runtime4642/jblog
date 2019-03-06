package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.dto.CategoryCountDto;
import com.douzone.dto.JSONResult;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Auth
@Controller
@RequestMapping("/categoryAdmin")
public class CategoryAdminController {
	
	@Autowired
	private CategoryService categorySerivce;
	
	@RequestMapping("/blog-admin-category")
	public String adminCategory() {
		return "blog/blog-admin-category";
	}
	
	@ResponseBody
	@RequestMapping(value="/category",method=RequestMethod.POST)
	public JSONResult writeCategory(@AuthUser UserVo authuser,@ModelAttribute CategoryVo categoryVo) {
		categoryVo.setUserNo(authuser.getNo());
		//이미 동일한 이름의 카테고리가 존재한다면
		if(categorySerivce.getCategoryNo(categoryVo) != null)
		{
			return null;
		}
		if(categorySerivce.write(categoryVo))
			return JSONResult.success(categoryVo);
		return null;
	}
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public JSONResult deleteCategory(@RequestParam(value="no",required=false) int no) {
		if(categorySerivce.delete(no))
		return JSONResult.success(no);
		else
			return JSONResult.success(null);
	}
	
	@ResponseBody
	@RequestMapping("/getcategory")
	public JSONResult getCategory(@AuthUser UserVo authUser) {
		return JSONResult.success(categorySerivce.getCategoryCountList(authUser.getNo()));
	}
	
	

}
