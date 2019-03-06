package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.CommentVo;
import com.douzone.jblog.vo.PostVo;


@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	
	@RequestMapping({"/{userNo}","/{userNo}/{postNo}","/{userNo}/{postNo}/{categoryNo}"})
	public String main(Model model,@PathVariable int userNo,@PathVariable Optional<Integer> postNo,@PathVariable Optional<Integer> categoryNo) {
		List<PostVo> postList = null;
		PostVo postVo = null;
		//postNo와 category 입력이 없다면 최근작성한 리스트 출력
		if(!postNo.isPresent()&&!categoryNo.isPresent())
		{
			postList=blogService.getPostList(userNo);
			if(postList.size() != 0) {
				postVo =postList.get(0);
			model.addAttribute("commentList",blogService.getCommentList(postVo.getNo()));
			}
		}
		//게시글 목록을 누른경우
		else if(postNo.isPresent()&&!categoryNo.isPresent()){
			postList=blogService.getPostList(userNo);
			postVo =blogService.getPostByNoAndUserNo(postNo.get(),userNo);
			model.addAttribute("commentList",blogService.getCommentList(postVo.getNo()));
		}
		//카테고리를 누른경우
		else if(postNo.isPresent()&&categoryNo.isPresent()){
					postList=blogService.getPostList(categoryNo.get());
					postVo =blogService.getPostByNoAndUserNo(postNo.get(),userNo);
					model.addAttribute("commentList",blogService.getCommentList(postVo.getNo()));
					model.addAttribute("categoryClick",true);
					model.addAttribute("categoryNo",categoryNo.get());
		}
		
		model.addAttribute("postVo",postVo);
		model.addAttribute("postList",postList);
		model.addAttribute("blogVo",blogService.getBlog(userNo));
		model.addAttribute("categoryList",blogService.getList(userNo));
		model.addAttribute("userNo",userNo);
		
		return "blog/blog-main";
	}
	
	@RequestMapping(value="/writeComment",method=RequestMethod.POST)
	public String writeComment(@ModelAttribute CommentVo commentVo,@RequestParam(value="url")String url) {
		blogService.writeComment(commentVo);
		return "redirect:"+url;
	}
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(@RequestParam(value="no")int no,@RequestParam(value="url")String url) {
		
		blogService.deleteComment(no);
		return "redirect:"+url;
	}
	
	
	
	//option title,content,regDate... url로 받을때 예시로 주석으로 남겨둠
//	@RequestMapping({"/{userNo}","/{userNo}/{title}/{content}/{regDate}","/{userNo}/{title}/{content}/{regDate}/{category}"})
//	public String main(Model model,@PathVariable int userNo,@PathVariable Optional<String> title, @PathVariable Optional<String> content,
//			@PathVariable Optional<String> regDate,@PathVariable Optional<String> category) {
//		
//		List<PostVo> postList = null;
//		//title 과 content url이 null이 아님
//		if(title.isPresent() && content.isPresent() && regDate.isPresent()) {
//			String titleStr = title.get();
//			String contentStr = content.get();
//			String regDateStr = regDate.get();
//			model.addAttribute("title", titleStr);
//			model.addAttribute("content", contentStr);
//			model.addAttribute("regDate",regDateStr);
//			
//			//카테고리를 누른경우가 아님
//			if(!category.isPresent()) {
//			//제일 최근에 작성한 순서대로 리스트를 가져온다
//			 postList =blogService.getList();
//			}
//			//카테고리를 눌렀을 경우
//			else {
//				postList=blogService.getList(category.get());
//			}
//		}
//		//null 일 경우에는 최근에 쓴글을 가지고 와야함.
//		else {
//			postList =blogService.getList();
//			model.addAttribute("title",postList.get(0).getTitle());
//			model.addAttribute("content",postList.get(0).getContent());
//			model.addAttribute("regDate",postList.get(0).getRegDate());
//		}
//		model.addAttribute("postList",postList);
//		model.addAttribute("blogVo",blogService.getBlog(userNo));
//		model.addAttribute("categoryList",blogService.getList(userNo));
//		model.addAttribute("userNo",userNo);
//		return "blog/blog-main";
//	}
}
