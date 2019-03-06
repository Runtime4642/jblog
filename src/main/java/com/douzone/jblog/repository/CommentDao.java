package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CommentVo;


@Repository
public class CommentDao {
	@Autowired
	private SqlSession sqlSession;

	public boolean insert(CommentVo vo)
	{
		return 1==sqlSession.insert("comment.insert",vo);
	}
	
	public List<CommentVo> getList(int postNo){
		return sqlSession.selectList("comment.select",postNo);
	}
	
	public boolean delete(int no) {
		return 1==sqlSession.delete("comment.delete",no);
	}
	
	
	
}
