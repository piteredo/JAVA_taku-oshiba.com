package com.takuoshiba;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Wp_posts {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer ID;
    private String post_title;
    private String post_date;
    private String post_content;
    private String post_status;
    private Integer post_parent;
    private String post_mime_type;
    private String guid;

	public Integer getID() {
		return ID;
	}
	
	public String getPost_title() {
		return post_title;
	}
	
	public String getPost_date() {
		String result = post_date.substring(0, 10);
		return result;
	}
	
	public String getPost_content() {
		return post_content;
	}
	
	public String getGuid() {
		return guid;
	}
	
	public String getPost_status() {
		return post_status;
	}
	
	public Integer getPost_parent() {
		return post_parent;
	}
	
	public String getPost_mime_type() {
		return post_mime_type;
	}
}