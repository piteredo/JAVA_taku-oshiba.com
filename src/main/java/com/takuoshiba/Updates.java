package com.takuoshiba;

import java.time.LocalDate;

public class Updates implements Comparable<Updates> {
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
		String result = text.replaceAll("<(\"[^\"]*\"|'[^']*'|[^'\">])*>", "");
		return result;
	}
	
	public String getImgSrc() {
		return imgSrc;
	}
	
	public String getUrl() {
		return url;
	}
	
	public int compareTo(Updates obj) {
		String[] liStr = updateDate.split("-");
	    LocalDate scheDate = LocalDate.of(
	      Integer.parseInt(liStr[0]),
	      Integer.parseInt(liStr[1]),
	      Integer.parseInt(liStr[2])
	      );
	    String[] liStr2 = obj.getUpdateDate().split("-");
	    LocalDate scheDate2 = LocalDate.of(
	      Integer.parseInt(liStr2[0]),
	      Integer.parseInt(liStr2[1]),
	      Integer.parseInt(liStr2[2])
	      );
		return scheDate2.compareTo(scheDate);
	}
}
