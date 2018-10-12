package com.takuoshiba;

public class Updates {
	private String title;
	private String updateDate;
	private String text;
	private String imgSrc;
	private String url;
	
	public Updates(String title, String updateDate, String text, String imgSrc, String url) {
		this.title = title;
		this.updateDate = updateDate;
		this.text = text;
		this.imgSrc = imgSrc;
		this.url = url;
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
	
	public String getUrl() {
		return url;
	}
}
