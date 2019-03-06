package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.repository.UserDao;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Transactional
	public boolean join(UserVo userVo) {
		//회원 insert에 성공하면 blog도 만듬
		if(userDao.insert(userVo)) {
			int userNo = userDao.lastInsertId();
			blogDao.insert(userNo);
			categoryDao.defaultInsert(userNo);
			return true;
		}
		
		return false;
	}
	public UserVo login(UserVo userVo) {
		return	userDao.getByIdAndPassword(userVo);
	}
	public boolean userSearch(String id) {
		if(userDao.selectById(id)==null)
			return false;
		else
			return true;
	}

}
