package com.takuoshiba;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Place {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String city;
    private String name;
    private String url;
    
	public Integer getId() {
		return id;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getName() {
		return name;
	}
	
	public String getUrl() {
		return url;
	}
}