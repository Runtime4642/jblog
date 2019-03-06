package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<PostVo> selectByUserNo(int userNo) {
		return sqlSession.selectList("post.select",userNo);
	}
	
	public List<PostVo> selectByCategoryNo(Integer categoryNo) {
		return sqlSession.selectList("post.selectBycategoryNo",categoryNo);
	}
	
	public PostVo selectByNoAndUserNo(int no,int userNo)
	{
		Map <String,Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("userNo", userNo);
		return sqlSession.selectOne("post.selectByNoAndUserNo",map);
	}
}
