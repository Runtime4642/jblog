package com.douzone.jblog.vo;

public class BlogVo {
	
	private int userNo;
	private String title;
	private String logo;
	@Override
	public String toString() {
		return "BlogVo [userNo=" + userNo + ", title=" + title + ", logo=" + logo + "]";
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}

}
