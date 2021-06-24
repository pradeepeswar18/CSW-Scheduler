package com.csw.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.csw.entity.Employee;
import com.csw.entity.ScheduleTrigger;
import com.csw.model.EmployeeDTO;

public interface ScheduleRepository {
	public void addSchedule(Employee employee);
	public void addScheduleTriggers(List<ScheduleTrigger> schedulerTriggerList);
	public Employee getEmployee(String employeeId);
	public boolean isExistingEmployee(String employeeId);
	public void createSchedule(Employee employee);
	public List<ScheduleTrigger> getSchedule(LocalDate date);
	public void updateSchedule(EmployeeDTO employeeDTO);
	public Integer cancelSchedule(String employeeId, LocalDate startDate, LocalDate endDate);
	public Integer cancelSchedule(String employeeId, LocalDate startDate, LocalDate endDate, LocalTime time);
	public void cancelSchedule(Employee employee);
	public Integer cancelScheduleByDate(String employeeId, LocalDate date);
	public Integer cancelScheduleByDate(String employeeId, LocalDate date, LocalTime time);
}
