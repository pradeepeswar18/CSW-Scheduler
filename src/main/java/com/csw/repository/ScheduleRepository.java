package com.csw.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.csw.entity.Schedule;
import com.csw.model.ScheduleFrequency;

public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Integer>{
	
	@Query("delete from Schedule s where s.employeeId = ?1 AND s.startDate = ?2 AND s.endDate = ?3")
	@Modifying
	Integer cancelSchedule(String employeeId, LocalDate startDate, LocalDate endDate);
	
	@Query("delete from Schedule s where s.employeeId = ?1 AND s.startDate = ?2 AND s.endDate = ?3 AND s.time = ?4")
	@Modifying
	Integer cancelSchedule(String employeeId, LocalDate startDate, LocalDate endDate, LocalTime time);
	
	@Query("update Schedule s set s.time = ?4 where s.employeeId = ?1 AND s.startDate = ?2 AND s.endDate= ?3 "
			+ "AND s.duration = ?5 AND s.scheduleFrequency = ?6")
	@Modifying
	void updateTime(String employeeId, LocalDate startDate, LocalDate endDate, LocalTime time, Integer duration, 
			ScheduleFrequency frequency);
}