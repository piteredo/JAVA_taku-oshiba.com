package com.takuoshiba;

import org.springframework.data.repository.CrudRepository;

import com.takuoshiba.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {
	
}