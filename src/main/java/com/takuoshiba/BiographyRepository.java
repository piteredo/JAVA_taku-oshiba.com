package com.takuoshiba;

import org.springframework.data.repository.CrudRepository;

import com.takuoshiba.Biography;

public interface BiographyRepository extends CrudRepository<Biography, Integer> {
	
}