package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;

	
	public boolean insert(PostVo postVo) {
		return 1==sqlSession.insert("post.insert",postVo);
	}
	
	public List<PostVo> select() {
		return sqlSession.selectList("post.select");
	}
	
	public List<PostVo> selectByCategoryNo(Integer categoryNo) {
		return sqlSession.selectList("post.selectBycategoryNo",categoryNo);
	}
	
	public PostVo select(int no)
	{
		return sqlSession.selectOne("post.selectByNo",no);
	}
}
