package com.csw.model;

import java.time.LocalDate;
import java.time.LocalTime;


public class ScheduleTriggerDTO {
	private String employeeId;
	private LocalDate date;
	private LocalTime time;
	private Integer duration;	
	
	
	public ScheduleTriggerDTO(String employeeId, LocalDate date, LocalTime time, Integer duration) {
		this.employeeId = employeeId;
		this.date = date;
		this.time = time;
		this.duration = duration;
	}

	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public LocalTime getTime() {
		return time;
	}
	
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	public Integer getDuration() {
		return duration;
	}
	
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	
}
