package com.csw.service;

import java.util.List;

import com.csw.exception.CswSchedulerException;
import com.csw.model.EmployeeDTO;
import com.csw.model.ScheduleTriggerDTO;

public interface SchedulerService {
	public void addSchedule(EmployeeDTO employeeDTO) throws CswSchedulerException;
	public EmployeeDTO getEmployee(String employeeId) throws CswSchedulerException;
	public List<ScheduleTriggerDTO> getSchedule(String dateString) throws CswSchedulerException;
	public void updateSchedule(EmployeeDTO employeeDTO) throws CswSchedulerException;
	public Integer cancelSchedule(String employeeId, String startDateString, String endDateString) throws CswSchedulerException;
	public Integer cancelSchedule(String employeeId, String startDateString, String endDateString, String timeString) throws CswSchedulerException;
	public Integer cancelSchedule(String employeeId) throws CswSchedulerException;
	public Integer cancelScheduleByDate(String employeeId, String dateString) throws CswSchedulerException;
	public Integer cancelScheduleByDate(String employeeId, String dateString, String timeString) throws CswSchedulerException;
}
