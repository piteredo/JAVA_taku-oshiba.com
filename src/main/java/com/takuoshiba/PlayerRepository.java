package com.takuoshiba;

import org.springframework.data.repository.CrudRepository;

import com.takuoshiba.Player;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
	
}