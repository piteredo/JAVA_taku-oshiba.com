package com.takuoshiba;

import org.springframework.data.repository.CrudRepository;

import com.takuoshiba.Wp_posts;

public interface Wp_postsRepository extends CrudRepository<Wp_posts, Integer> {
	
}