package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;
	
	public List<CategoryVo>getCategoryList(int userNo){
		return categoryDao.getList(userNo);
	}
	
	public boolean postWrite(CategoryVo categoryVo,PostVo postVo) {
		postVo.setCategoryNo(categoryDao.getCategoryNo(categoryVo));
		return postDao.insert(postVo);
	}
	

}
