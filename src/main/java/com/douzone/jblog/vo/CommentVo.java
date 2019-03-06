package com.douzone.jblog.vo;

public class CommentVo {
	private int no;
	private String content;
	private String regDate;
	private String postNo;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	@Override
	public String toString() {
		return "CommentVo [no=" + no + ", content=" + content + ", regDate=" + regDate + ", postNo=" + postNo + "]";
	}
	

}
