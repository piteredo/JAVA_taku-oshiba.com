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
		
		//Youtube List (All
		mav.addObject("youtubeList", getYoutubeList());
		
		//Desgin Update Date - List (All / Reverse
		mav.addObject("designUpdateDate", getDesignUpdateDate());
		mav.addObject("designList", getDesignReverseOrderList());
		
		//UpdatList
		List<Updates> updateList = new ArrayList<>();		
		
		//Blog List (WP_POSTS_LENGTH / post_status == "publish"
		final int WP_POSTS_LENGTH = 5;
		Iterable<Wp_posts> wp_postsSubList = getWp_postsSubList(WP_POSTS_LENGTH);
		Iterator<Wp_posts> wpIt = wp_postsSubList.iterator();
		while(wpIt.hasNext()) {
			Wp_posts t = wpIt.next();
			updateList.add(new Updates(
					"[BLOG] " + t.getPost_title(),
					t.getPost_date(),
					t.getPost_content(),
					getWp_postsImgUrl(t),
					t.getGuid()
				));
		}
		
		//Bio
		updateList.add(new Updates(
				"[BIO] " + getBiography().getJaname(),
				getBiography().getUpdatedate(),
				getBiography().getJatextplane(),
				"https://taku-oshiba.com/img/bio/" + getBiographyPhoto().getSrc() + ".jpg",
				"https://taku-oshiba.com/biography"
			));
		
		//Design (Latest
		updateList.add(new Updates(
				"[DESIGN] 過去デザイン",
				getDesignUpdateDate(),
				"デザイン更新しました。",
				"https://taku-oshiba.com/img/design/" + getLatestDesign().getSrc() + ".jpg",
				"https://taku-oshiba.com/design"
			));
		
		//Schedule (Current One
		updateList.add(new Updates(
				"[SCHEDULE] 次回出演予定",
				getScheduleUpdateDate(),
				getCurrentSchedule().getAll(),
				"https://taku-oshiba.com/img/design/" + getCurrentSchedule().getImgurlWithNoImage(),
				"https://taku-oshiba.com/schedule"
			));		
		
		//Discography (Current One
		updateList.add(new Updates(
				"[DISCO] 最新CD",
				getDiscographyUpdateDate(),
				getLatestDiscography().getTitle(),
				"https://taku-oshiba.com/img/design/" + getLatestDiscography().getImgurl() + ".jpg",
				"https://taku-oshiba.com/discography"
			));		
		
		//Sort + Add UpdateList (Compare with date
		Collections.sort(updateList);
		
		mav.addObject("updates", updateList);
		mav.setViewName("index.html");
		return mav;
	}	
	
	@GetMapping(path="/biography")
	public ModelAndView getBiography(ModelAndView mav){		
		mav.addObject("biography", getBiography());
		mav.addObject("photoList", getBiographyPhotoList());
		mav.addObject("updateDate", getBiography().getUpdatedate());		
		mav.setViewName("biography.html");
		return mav;
	}	
	
	@GetMapping(path="/schedule")
	public ModelAndView getSchedule(ModelAndView mav){		
		mav.addObject("scheduleList", getScheduleList());
		mav.addObject("updateDate", getScheduleUpdateDate());		
		mav.addObject("placeList", getPlaceList());		
		mav.addObject("playerList", getPlayerList());
		mav.setViewName("schedule.html");
		return mav;
	}	
	
	@GetMapping(path="/design")
	public ModelAndView getDesign(ModelAndView mav){
		mav.addObject("designList", getDesignReverseOrderList());
		mav.addObject("updateDate", getDesignUpdateDate());		
		mav.setViewName("design.html");
		return mav;
	}	
	
	@GetMapping(path="/discography")
	public ModelAndView getDiscography(ModelAndView mav){
		mav.addObject("discographyList", getDiscographyReverseOrderList());
		mav.addObject("updateDate", getDiscographyUpdateDate());		
		mav.addObject("playerList", getPlayerList());		
		mav.setViewName("discography.html");
		return mav;
	}	
	
	private Iterable<Youtube> getYoutubeList() {
		return youtubeRepository.findAll();
	}
	
	private String getDesignUpdateDate() {
		Design designIdOne = designRepository.findById(1).orElse(null);
		if(designIdOne == null) {
			return "";
		}
		return designIdOne.getUpdatedate();
	}
	
	private <T> List<T> reverseSort(Iterator<T> it) {
		List<T> result = new ArrayList<>();
		while(it.hasNext()) {
			T t = it.next();
			result.add(0, t);
		}
		return result;
	}
	
	private List<Design> getDesignReverseOrderList() {
		return reverseSort(designRepository.findAll().iterator());
	}
	
	private Iterable<Wp_posts> getWp_postsSubList(int postsLength) {		
		List<Wp_posts> list = reverseSort(wp_postsRepository.findAll().iterator());
		
		List<Wp_posts> result = new ArrayList<>();
		Iterator<Wp_posts> it = list.iterator();
		while(it.hasNext()) {
			Wp_posts t = it.next();
			if(t.getPost_status().equals("publish")) {
				result.add(t);
			}
		}
		return result.subList(0, postsLength);
	}
	
	private String getWp_postsImgUrl(Wp_posts post) {
		Iterator<Wp_posts> allPostIt = wp_postsRepository.findAll().iterator();
		while(allPostIt.hasNext()) {
			Wp_posts t = allPostIt.next();
			if(post.getID().equals(t.getPost_parent()) && t.getPost_mime_type().equals("image/jpeg")) {
				return t.getGuid();
			}
		}
		return "";
	}
	
	private Biography getBiography() {
		return biographyRepository.findById(1).orElse(null);
	}
	
	private Photo getBiographyPhoto() {
		return photoRepository.findById(1).orElse(null);
	}
	
	private Iterable<Photo> getBiographyPhotoList() {
		return photoRepository.findAll();
	}
	
	private Design getLatestDesign() {
		return getDesignReverseOrderList().get(0);
	}
	
	private String getScheduleUpdateDate() {
		Schedule scheduleIdOne = scheduleRepository.findById(1).orElse(null);
		if(scheduleIdOne == null) {
			return "";
		}
		return scheduleIdOne.getUpdatedate();
	}
	
	private List<Schedule> getScheduleList() {		
		List<Schedule> result = new ArrayList<>();
		LocalDate currentDate = LocalDate.now();
		Iterator<Schedule> it = scheduleRepository.findAll().iterator();
		while(it.hasNext()) {
			Schedule t = it.next();
			String dStr = t.getDate();
			String[] liStr = dStr.split("-");
		    LocalDate scheDate = LocalDate.of(
		      Integer.parseInt(liStr[0]),
		      Integer.parseInt(liStr[1]),
		      Integer.parseInt(liStr[2])
		      );
		    if(scheDate.compareTo(currentDate) >= 0) {
		    	result.add(t);
		    }
		}
		return result;
	}
	
	private Schedule getCurrentSchedule() {
		return getScheduleList().iterator().next();
	}
	
	private List<Discography> getDiscographyReverseOrderList() {
		return reverseSort(discographyRepository.findAll().iterator());
	}
	
	private Discography getLatestDiscography() {
		return getDiscographyReverseOrderList().iterator().next();
	}
	
	private String getDiscographyUpdateDate() {
		Discography discographyIdOne = discographyRepository.findById(1).orElse(null);
		if(discographyIdOne == null) {
			return "";
		}
		return discographyIdOne.getUpdatedate();
	}
	
	private Iterable<Place> getPlaceList() {
		return placeRepository.findAll();
	}
	
	private Iterable<Player> getPlayerList() {
		return playerRepository.findAll();
	}
}