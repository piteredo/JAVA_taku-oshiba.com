package com.takuoshiba;

import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.takuoshiba.Schedule;
import com.takuoshiba.Place;
import com.takuoshiba.Player;
import com.takuoshiba.ScheduleRepository;
import com.takuoshiba.PlaceRepository;
import com.takuoshiba.PlayerRepository;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

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
	
	@GetMapping(path="/")
	public ModelAndView getIndex(ModelAndView mav){
		
		Iterable<Youtube> youtubeList = youtubeRepository.findAll();		
		mav.addObject("youtubeList", youtubeList);
		
		Iterable<Design> designList = designRepository.findAll();
		List<Design> tempList = new ArrayList<>();		
		String designUpdatedate = "";
		Iterator<Design> it = designList.iterator();
		while(it.hasNext()) {
			Design s = it.next();
		    if(s.getUpdatedate() != null) {
		    	designUpdatedate = s.getUpdatedate();
		    }
		    tempList.add(0, s);
		}
		/*List<Design> tempList2 = new ArrayList<>();
		for(int i=0; i<5; i++) {
			tempList2.add(tempList.get(i));
		}*/
		Iterable<Design> designList2 = tempList;
		
		mav.addObject("designList", designList2);
		mav.addObject("designUpdatedate", designUpdatedate);
		
		Iterable<Biography> biographyList = biographyRepository.findAll();
		Iterable<Photo> photoList = photoRepository.findAll();		
		Iterator<Biography> it2 = biographyList.iterator();
		Iterator<Photo> it3 = photoList.iterator();
		Biography biography = it2.next();
		Photo photo = it3.next();
		String biographyUpdatedate = biography.getUpdatedate();		
		
		List<Biography> bi = new ArrayList<>();
		bi.add(biography);
		bi.add(biography);
		bi.add(biography);
		bi.add(biography);
		bi.add(biography);
		
		mav.addObject("biography", bi);
		mav.addObject("photo", photo);
		mav.addObject("biographyUpdatedate", biographyUpdatedate);
		
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