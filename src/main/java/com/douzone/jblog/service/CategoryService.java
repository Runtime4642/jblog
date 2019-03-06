package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.dto.CategoryCountDto;
import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryCountDto> getCategoryCountList(int userNo) {
		return categoryDao.getCategoryCountList(userNo);
	}
	
	public boolean write(CategoryVo categoryVo) {
		return categoryDao.insert(categoryVo);
	}
	
	public boolean delete(int no)
	{
		return categoryDao.delete(no);
	}
	
	public Integer getCategoryNo(CategoryVo categoryVo) {
		return categoryDao.getCategoryNo(categoryVo);
	}
	
	
	
}
