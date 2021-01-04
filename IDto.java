package com.exhibition.project.BoardDto;

public class IDto {
	private int item_num;
	private String category;
	private String itemtopic;
	private String file_dir;
	private String item_comment;
	private String userid;
	private int like_cnt;
	
	public IDto() {};
	public IDto(int item_num, String category, String itemtopic, String file_dir, String item_comment, String userid,
			int like_cnt) {
		super();
		this.item_num = item_num;
		this.category = category;
		this.itemtopic = itemtopic;
		this.file_dir = file_dir;
		this.item_comment = item_comment;
		this.userid = userid;
		this.like_cnt = like_cnt;
	}
	public int getItem_num() {
		return item_num;
	}
	public String getCategory() {
		return category;
	}
	public String getItemtopic() {
		return itemtopic;
	}
	public String getFile_dir() {
		return file_dir;
	}
	public String getItem_comment() {
		return item_comment;
	}
	public String getUserid() {
		return userid;
	}
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setItemtopic(String itemtopic) {
		this.itemtopic = itemtopic;
	}
	public void setFile_dir(String file_dir) {
		this.file_dir = file_dir;
	}
	public void setItem_comment(String item_comment) {
		this.item_comment = item_comment;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
}
