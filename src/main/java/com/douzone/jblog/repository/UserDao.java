package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(UserVo userVo) {
		return 1==sqlSession.insert("user.insert",userVo);
	}
	
	public UserVo getByIdAndPassword(UserVo userVo)
	{
		return sqlSession.selectOne("user.getByIdAndPassword",userVo);
	}
	
	public int lastInsertId() {
		return sqlSession.selectOne("user.lastInsertId");
	}
	public UserVo selectById(String id)
	{
		return sqlSession.selectOne("user.selectById",id);
	}
}
