package com.takuoshiba;

public class Updates {
	private String title;
	private String updateDate;
	private String text;
	private String imgSrc;
	
	public Updates(String title, String updateDate, String text, String imgSrc) {
		this.title = title;
		this.updateDate = updateDate;
		this.text = text;
		this.imgSrc = imgSrc;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getUpdateDate() {
		return updateDate;
	}
	
	public String getText() {
		return text;
	}
	
	public String getImgSrc() {
		return imgSrc;
	}
}
