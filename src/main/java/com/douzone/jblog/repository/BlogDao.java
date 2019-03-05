package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.dto.CategoryCountDto;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;

	
	public BlogVo selectByUserVo(UserVo userVo)
	{
		return sqlSession.selectOne("blog.selectByUserVo",userVo);
	}
	
	public boolean insert(int userNo)
	{
		return 1==sqlSession.insert("blog.insert",userNo);
	}
	public boolean update(BlogVo blogVo) {
		return 1==sqlSession.update("blog.update",blogVo);
	}
	public BlogVo selectByUserNo(int userNo)
	{
		return sqlSession.selectOne("blog.selectByUserNo",userNo);
	}

}
