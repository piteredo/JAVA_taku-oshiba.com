package com.takuoshiba;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Biography {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String janame;
    private String jatext;
    private String enname;
    private String entext;
    private String updatedate;

	public Integer getId() {
		return id;
	}
	
	public String getJaname() {
		return janame;
	}
	
	public String getJatext() {
		return jatext;
	}
	
	public String getJatextplane() {
		String result = jatext.replace("<br/>", "");
		return result;
	}
	
	public String getEnname() {
		return enname;
	}
	
	public String getEntext() {
		return entext;
	}
	
	public String getUpdatedate() {
		return updatedate;
	}
}