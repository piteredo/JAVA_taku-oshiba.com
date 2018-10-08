package com.takuoshiba;

import org.springframework.data.repository.CrudRepository;

import com.takuoshiba.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {
	
}