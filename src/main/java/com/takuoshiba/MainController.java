package com.takuoshiba;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.takuoshiba.Updates;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

@Controller
public class MainController {
	private @Autowired YoutubeRepository youtubeRepository;
	private @Autowired BiographyRepository biographyRepository;
	private @Autowired PhotoRepository photoRepository;
	private @Autowired ScheduleRepository scheduleRepository;
	private @Autowired PlaceRepository placeRepository;
	private @Autowired PlayerRepository playerRepository;
	private @Autowired DesignRepository designRepository;
	private @Autowired DiscographyRepository discographyRepository;
	
	private @Autowired Wp_postsRepository wp_postsRepository;
	
	@GetMapping(path="/index")
	public ModelAndView getIndex(ModelAndView mav){
		
		
		
		Iterable<Youtube> youtubeList = youtubeRepository.findAll();
		mav.addObject("youtubeList", youtubeList);
		
		Iterable<Design> designList = designRepository.findAll();
		List<Design> tempList = new ArrayList<>();		
		String designUpdatedate = "";
		Iterator<Design> it = designList.iterator();
		int cn = 0;
		while(it.hasNext()) {
			Design s = it.next();
		    if(s.getUpdatedate() != null) {
		    	designUpdatedate = s.getUpdatedate();
		    }
		    tempList.add(0, s);
		}
		List<Design> tempList5 = new ArrayList<>();	
		Iterator<Design> it6 = tempList.iterator();
		while(it6.hasNext() && cn<5) {
			Design s = it6.next();
		    tempList5.add(s);
		    cn++;
		}
		Iterable<Design> designList2 = tempList5;		
		mav.addObject("designList", designList2);
		mav.addObject("designUpdatedate", designUpdatedate);
		
		
		
		
		List<Updates> updateList = new ArrayList<>();
		
		
		Iterable<Wp_posts> wp_postsList = wp_postsRepository.findAll();
		List<Wp_posts> tempList2 = new ArrayList<>();
		
		Iterator<Wp_posts> it2 = wp_postsList.iterator();
		while(it2.hasNext()) {
			Wp_posts s = it2.next();
			if(s.getPost_status().equals("publish")) {
				tempList2.add(0, s);
			}
		}
		
		Iterator<Wp_posts> it3 = tempList2.iterator();
		int counter = 0;
		String url = "";
		while(it3.hasNext() && counter<5) {
			Wp_posts s = it3.next();
			Iterator<Wp_posts> it4 = wp_postsList.iterator();
			while(it4.hasNext()) {
				Wp_posts ss = it4.next();
				if(s.getID().equals(ss.getPost_parent()) && ss.getPost_mime_type().equals("image/jpeg")) {
					url = ss.getGuid();
				}
			}
			updateList.add(new Updates(s.getPost_title(), s.getPost_date(), s.getPost_content(), url, s.getGuid()));
			counter++;
		}
		
		Biography bi = biographyRepository.findAll().iterator().next();
		Photo ph = photoRepository.findAll().iterator().next();		
		updateList.add(new Updates(bi.getJaname(),	bi.getUpdatedate(),	bi.getJatextplane(), "https://taku-oshiba.com/img/bio/"+ph.getSrc()+".jpg", "https://taku-oshiba.com/biography"));
		
		Design de = tempList.iterator().next();	
		updateList.add(new Updates("デザイン",	designUpdatedate,	"デザイン更新しました。",	"https://taku-oshiba.com/img/design/"+de.getSrc()+".jpg", "https://taku-oshiba.com/design"));
		
		String updatedate = "";
		Iterable<Schedule> scheduleListAll = scheduleRepository.findAll();
		List<Schedule> scheduleListTemp = new ArrayList<>();
		LocalDate currentDate = LocalDate.now();
		Iterator<Schedule> it9 = scheduleListAll.iterator();
		while(it9.hasNext()) {
			Schedule s = it9.next();		
			String dStr = s.getDate();
			String[] liStr = dStr.split("-");
		    LocalDate scheDate = LocalDate.of(
		      Integer.parseInt(liStr[0]),
		      Integer.parseInt(liStr[1]),
		      Integer.parseInt(liStr[2])
		      );
		    if(s.getUpdatedate() != null) {
		    	updatedate = s.getUpdatedate();
		    }		    
		    if(scheDate.compareTo(currentDate) >= 0) {
		    	scheduleListTemp.add(s);
		    }
		}
		Iterable<Schedule> scheduleList = scheduleListTemp;
		Schedule sc = scheduleList.iterator().next();
		updateList.add(new Updates("次回出演予定", updatedate, sc.getAll(), "https://taku-oshiba.com/img/design/"+sc.getImgurl(), "https://taku-oshiba.com/schedule"));
		
		
		Collections.sort(updateList);
		
		
		
		
		
		mav.addObject("updates", updateList);
		
		
		mav.setViewName("index.html");
		return mav;
	}
	
	
	@GetMapping(path="/biography")
	public ModelAndView getBiography(ModelAndView mav){
		
		Iterable<Biography> biographyList = biographyRepository.findAll();
		Iterable<Photo> photoList = photoRepository.findAll();
		
		Iterator<Biography> it = biographyList.iterator();
		Biography biography = it.next();		
		String updatedate = biography.getUpdatedate();
		
		mav.addObject("biography", biography);
		mav.addObject("photoList", photoList);
		mav.addObject("updateDate", updatedate);
		
		mav.setViewName("biography.html");
		return mav;
	}
	
	
	@GetMapping(path="/schedule")
	public ModelAndView getSchedule(ModelAndView mav){
		
		Iterable<Schedule> scheduleListAll = scheduleRepository.findAll();
		List<Schedule> scheduleListTemp = new ArrayList<>();
		
		String updatedate = "";
		LocalDate currentDate = LocalDate.now();
		Iterator<Schedule> it = scheduleListAll.iterator();
		while(it.hasNext()) {
			Schedule s = it.next();		
			String dStr = s.getDate();
			String[] liStr = dStr.split("-");
		    LocalDate scheDate = LocalDate.of(
		      Integer.parseInt(liStr[0]),
		      Integer.parseInt(liStr[1]),
		      Integer.parseInt(liStr[2])
		      );
		    
		    if(s.getUpdatedate() != null) {
		    	updatedate = s.getUpdatedate();
		    }
		    
		    if(scheDate.compareTo(currentDate) >= 0) {
		    	scheduleListTemp.add(s);
		    }
		}
		Iterable<Schedule> scheduleList = scheduleListTemp;		
		
		mav.addObject("scheduleList", scheduleList);
		mav.addObject("updateDate", updatedate);
		
		Iterable<Place> placeList = placeRepository.findAll();
		mav.addObject("placeList", placeList);
		
		Iterable<Player> playerList = playerRepository.findAll();
		mav.addObject("playerList", playerList);
		
		mav.setViewName("schedule.html");
		return mav;
	}
	
	
	@GetMapping(path="/design")
	public ModelAndView getDesign(ModelAndView mav){
		
		Iterable<Design> designList = designRepository.findAll();
		List<Design> tempList = new ArrayList<>();
		
		String updatedate = "";
		Iterator<Design> it = designList.iterator();
		while(it.hasNext()) {
			Design s = it.next();
		    if(s.getUpdatedate() != null) {
		    	updatedate = s.getUpdatedate();
		    }
		    tempList.add(0, s);
		}
		Iterable<Design> designList2 = tempList;
		
		mav.addObject("designList", designList2);
		mav.addObject("updateDate", updatedate);
		
		mav.setViewName("design.html");
		return mav;
	}
	
	
	@GetMapping(path="/discography")
	public ModelAndView getDiscography(ModelAndView mav){
		
		Iterable<Discography> discographyList = discographyRepository.findAll();
		List<Discography> tempList = new ArrayList<>();
		
		String updatedate = "";
		Iterator<Discography> it = discographyList.iterator();
		while(it.hasNext()) {
			Discography s = it.next();
		    if(s.getUpdatedate() != null) {
		    	updatedate = s.getUpdatedate();
		    }
		    tempList.add(0, s);
		}
		Iterable<Discography> discographyList2 = tempList;
		
		mav.addObject("discographyList", discographyList2);
		mav.addObject("updateDate", updatedate);
		
		Iterable<Player> playerList = playerRepository.findAll();
		mav.addObject("playerList", playerList);
		
		mav.setViewName("discography.html");
		return mav;
	}
}