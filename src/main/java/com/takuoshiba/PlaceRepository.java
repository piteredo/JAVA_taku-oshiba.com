package com.takuoshiba;

import org.springframework.data.repository.CrudRepository;

import com.takuoshiba.Place;

public interface PlaceRepository extends CrudRepository<Place, Integer> {
	
}