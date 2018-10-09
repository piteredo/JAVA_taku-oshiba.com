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

	public Integer getID() {
		return ID;
	}
	
	public String getPost_title() {
		return post_title;
	}
	
	public String getPost_date() {
		return post_date;
	}
	
	public String getPost_content() {
		return post_content;
	}
	
	public String getImgsource() {
		return "design/071220metro9";
	}
}