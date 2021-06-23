package com.csw.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.csw.model.ScheduleTriggerStatus;

@Entity
@Table(name="scheduletrigger")
public class ScheduleTrigger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer scheduleTriggerId;
	
	private LocalDate date;
	
	private LocalTime time;
	
	private Integer duration;
	
	private String employeeId;
	
//	@Enumerated(EnumType.STRING)
	@Transient
	private ScheduleTriggerStatus scheduleTriggerStatus;

	public Integer getScheduleTriggerId() {
		return scheduleTriggerId;
	}

	public void setScheduleTriggerId(Integer scheduleTriggerId) {
		this.scheduleTriggerId = scheduleTriggerId;
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

	public ScheduleTriggerStatus getScheduleTriggerStatus() {
		return scheduleTriggerStatus;
	}

	public void setScheduleTriggerStatus(ScheduleTriggerStatus scheduleTriggerStatus) {
		this.scheduleTriggerStatus = scheduleTriggerStatus;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
}
