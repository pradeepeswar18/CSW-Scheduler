package com.csw.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDTO {
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private LocalTime time;
	
	private Integer duration;
	
	private Boolean repeat;
	
	private String scheduleFrequency;
	
	public ScheduleDTO(LocalDate startDate, LocalDate endDate, LocalTime time, 
			Integer duration, Boolean repeat, String scheduleFrequency) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.time = time;
		this.duration = duration;
		this.repeat = repeat;
		this.scheduleFrequency = scheduleFrequency;
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
		return ScheduleFrequency.valueOf(scheduleFrequency);
	}

	public void setScheduleFrequency(ScheduleFrequency scheduleFrequency) {
		this.scheduleFrequency = scheduleFrequency.toString();
	}
	
	@Override
	public String toString() {
		return "\n Start Date: " + startDate + " End Date: " +
				endDate + " Time: " + time +" Duration: " + duration + " mins " + " Frequency: " + scheduleFrequency.toString() + "\n";
	}
	
	@Override
	public int hashCode() {
		return startDate.hashCode() + endDate.hashCode() + time.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		
		if(this.getClass() != obj.getClass()) return false;
		
		ScheduleDTO scheduleDTO = (ScheduleDTO) obj;
		
		if(scheduleDTO.getStartDate().equals(startDate)) {
			if(scheduleDTO.getEndDate().equals(endDate)) {
				if(scheduleDTO.getTime().equals(time)) {
					return true;
				}
			}
		}
		
		return false;
	}
}
