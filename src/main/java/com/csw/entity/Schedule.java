package com.csw.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.csw.model.ScheduleFrequency;

@Entity
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer scheduleId;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private LocalTime time;
	
	private Integer duration;
	
	private String employeeId;
	
	@Column(name="is_repeat")
	private Boolean repeat;
	
	@Enumerated(EnumType.STRING)
	private ScheduleFrequency scheduleFrequency;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="scheduleId")
	List<ScheduleTrigger> schedulerTriggerList;
	
	public Schedule() { }; 

	public Schedule(LocalDate startDate, LocalDate endDate, LocalTime time, Integer duration,
			Boolean repeat, ScheduleFrequency scheduleFrequency) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.time = time;
		this.duration = duration;
		this.repeat = repeat;
		this.scheduleFrequency = scheduleFrequency;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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

	public Boolean getRepeat() {
		return repeat;
	}

	public void setRepeat(Boolean repeat) {
		this.repeat = repeat;
	}

	public ScheduleFrequency getScheduleFrequency() {
		return scheduleFrequency;
	}

	public void setScheduleFrequency(ScheduleFrequency scheduleFrequency) {
		this.scheduleFrequency = scheduleFrequency;
	}
	
	public List<ScheduleTrigger> getSchedulerTriggerList() {
		return schedulerTriggerList;
	}

	public void setSchedulerTriggerList(List<ScheduleTrigger> schedulerTriggerList) {
		this.schedulerTriggerList = schedulerTriggerList;
	}
}
