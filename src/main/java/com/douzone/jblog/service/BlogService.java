package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.repository.CommentDao;
import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.CommentVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private CommentDao commentDao;
	

	
	public BlogVo getBlog(UserVo userVo) {
		return blogDao.selectByUserVo(userVo);
	}
	
	public boolean blogModify(BlogVo blogVo) {
		return blogDao.update(blogVo);
	}
	public BlogVo getBlog(int userNo) {
		return blogDao.selectByUserNo(userNo);
	}
	
	public List<CategoryVo> getList(int userNo){
		return categoryDao.getList(userNo);
	}
	
	public List<PostVo> getPostList(int userNo) {
		return postDao.selectByUserNo(userNo);
	}
	public List<PostVo> getPostList(Integer categoryNo) {
		return postDao.selectByCategoryNo(categoryNo);
	}
	
	public PostVo getPostByNoAndUserNo(int no,int userNo) {
		return postDao.selectByNoAndUserNo(no,userNo);
	}
	
	public boolean writeComment(CommentVo commentVo)
	{
		return commentDao.insert(commentVo);
	}
	
	public List<CommentVo> getCommentList(int postNo)
	{
		return commentDao.getList(postNo);
	}
	
	public boolean deleteComment(int no)
	{
		return commentDao.delete(no);
	}

}
