package com.csw.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.csw.entity.ScheduleTrigger;

public interface ScheduleTriggerRepository extends PagingAndSortingRepository<ScheduleTrigger, Integer>{
	List<ScheduleTrigger> findByDate(LocalDate date);
	
	@Query("delete from ScheduleTrigger s where s.employeeId = ?1 AND s.date = ?2")
	@Modifying
	Integer cancelScheduleByDate(String employeeId, LocalDate date);
	
	@Query("delete from ScheduleTrigger s where s.employeeId = ?1 AND s.date = ?2 AND time= ?3")
	@Modifying
	Integer cancelScheduleByDate(String employeeId, LocalDate date, LocalTime time);
}