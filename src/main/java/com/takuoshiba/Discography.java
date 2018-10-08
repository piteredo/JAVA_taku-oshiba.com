package com.takuoshiba;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Discography {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String title;
    private String performeridlist;
    private String info;
    private String songlist;
    private String amazonurl;
    private String imgurl;
    private String updatedate;

	public Integer getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public List<Integer> getPerformeridlist() {
		int[] intArray = Arrays.stream(performeridlist.split(","))
				.filter(s -> !s.isEmpty())
				.mapToInt(Integer::parseInt)
				.toArray();
		
		List<Integer> integerList = new ArrayList<Integer>();
		for(int i : intArray) {
			integerList.add(i);
		}
		return integerList;
	}
	
	public String getInfo() {
		return info;
	}
	
	public String getSonglist() {
		return songlist;
	}
	
	public String getAmazonurl() {
		return amazonurl;
	}
	
	public String getImgurl() {
		return imgurl;
	}
	
	public String getUpdatedate() {
		return updatedate;
	}
}