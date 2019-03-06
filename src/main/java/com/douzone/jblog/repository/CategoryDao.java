package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.dto.CategoryCountDto;
import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> getList(int userNo){
		return sqlSession.selectList("category.getListByUserNo",userNo);
	}
	
	public boolean defaultInsert(int userNo) {
		return 1==sqlSession.insert("category.defaultInsert",userNo);
	}
	
	public List<CategoryCountDto> getCategoryCountList(int userNo){
		return sqlSession.selectList("category.selectCategoryCountByUserNo",userNo);
	}
	
	
	public boolean insert(CategoryVo categoryVo) {
		return 1==sqlSession.insert("category.insert",categoryVo);
	}
	
	public boolean delete(int no) {
		return 1==sqlSession.delete("category.deleteByNo",no);
	}
	
	public Integer getCategoryNo(CategoryVo categoryVo) {
		return sqlSession.selectOne("category.getCategoryNo",categoryVo);
	}
}
