package com.csw.model;

import java.util.Set;

public class EmployeeDTO {
	private String employeeId;
	
	private Set<ScheduleDTO> scheduleList;
	
	public EmployeeDTO(String employeeId, Set<ScheduleDTO> scheduleList) {
		this.employeeId = employeeId;
		this.scheduleList = scheduleList;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public Set<ScheduleDTO> getScheduleList() {
		return scheduleList;
	}
	
	public void setScheduleList(Set<ScheduleDTO> scheduleList) {
		this.scheduleList = scheduleList;
	}
	
	@Override
	public String toString() {
		return "Employee ID: " + employeeId + "\n" + scheduleList;
	}
}
