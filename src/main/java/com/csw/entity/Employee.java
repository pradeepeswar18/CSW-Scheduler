package com.csw.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Employee {
	@Id
	private String employeeId;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="employeeId")
	private List<Schedule> scheduleList;
	
	
	public Employee() { }
	public Employee(String employeeId, List<Schedule> scheduleList) {
		this.employeeId = employeeId;
		this.scheduleList = scheduleList;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public List<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}
	
	public Boolean addSchedule(List<Schedule> scheduleList) {
		return this.scheduleList.addAll(scheduleList);
	}
}

