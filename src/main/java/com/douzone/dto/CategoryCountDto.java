package com.douzone.dto;

public class CategoryCountDto {
	private int no;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	private String name;
	private int count;
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "CategoryCountDto [no=" + no + ", name=" + name + ", count=" + count + ", description=" + description
				+ "]";
	}
	

}
