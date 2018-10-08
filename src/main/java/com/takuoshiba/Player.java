package com.takuoshiba;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Player {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String instrument;
    private String url;
    
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getInstrument() {
		return instrument;
	}
	
	public String getUrl() {
		return url;
	}
}