package com.takuoshiba;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
public class Schedule {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Integer ispickup;
    private String date;
    private String title;
    private Integer placeid;
    private String opentime;
    private String starttime;
    private String price;
    private String performeridlist;
    private String imgurl;
    private String updatedate;

	public Integer getId() {
		return id;
	}
	
	public boolean getIspickup() {
		boolean result = false;
		if(ispickup == 1) {
			result = true;
		}
		return result;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getDay() {
		String[] ymd = this.date.split("-");
		LocalDate ld = LocalDate.of(
	      Integer.parseInt(ymd[0]),
	      Integer.parseInt(ymd[1]),
	      Integer.parseInt(ymd[2])
	      );
		DayOfWeek dow = ld.getDayOfWeek();
		String result = dow.getDisplayName(TextStyle.SHORT, Locale.JAPANESE);
		return result;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Integer getPlaceid() {
		return placeid;
	}
	
	public String getOpentime() {
		return opentime;
	}
	
	public String getStarttime() {
		return starttime;
	}
	
	public String getPrice() {
		return price;
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
	
	public String getImgurl() {
		return imgurl;
	}
	
	public String getUpdatedate() {
		return updatedate;
	}
}