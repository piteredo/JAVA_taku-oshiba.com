package com.takuoshiba;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Design {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String src;
    private String updatedate;

	public Integer getId() {
		return id;
	}
	
	public String getSrc() {
		return src;
	}
	
	public String getUpdatedate() {
		return updatedate;
	}
}