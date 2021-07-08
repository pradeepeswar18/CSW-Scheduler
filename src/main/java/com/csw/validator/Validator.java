package com.csw.validator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.csw.exception.CswSchedulerException;
import com.csw.model.EmployeeDTO;
import com.csw.model.ScheduleDTO;
import com.csw.model.ScheduleFrequency;

public class Validator {
	public static void validate(EmployeeDTO employeeDTO) throws CswSchedulerException{
		if(employeeDTO == null) throw new CswSchedulerException("Validator.EMPLOYEE_ID_NULL");
		Set<ScheduleDTO> scheduleList = employeeDTO.getScheduleList();
		validateEmployeeId(employeeDTO.getEmployeeId());
		for(ScheduleDTO scheduleDTO: scheduleList) {
			validateScheduleDate(scheduleDTO.getStartDate(), scheduleDTO.getEndDate());
			validateScheduleStartTime(scheduleDTO.getTime());
			validateScheduleDuration(scheduleDTO.getDuration());
			validateScheduleRepeat(scheduleDTO.getRepeat());
//			if(!validateScheduleFrequency(scheduleDTO.getScheduleFrequency())) 
//				throw new CswSchedulerException("Validator.INVALID_SCHEDULE_FREQUENCY");
		}
	}
	
	public static void validateEmployeeId(String employeeId) throws CswSchedulerException {
		if(!((employeeId != null) && (employeeId.matches("[\\w]+@[a-z]+[.](com|in)")))) 
			throw new CswSchedulerException("Validator.INVALID_EMAIL_ID");
	}
	
	public static void validateScheduleDate(LocalDate startDate, LocalDate endDate) throws CswSchedulerException{
		if(! (startDate!= null && endDate!=null &&
				startDate.isAfter(LocalDate.now()) &&
				endDate.compareTo(startDate)>=0))
			throw new CswSchedulerException("Validator.INVALID_START_OR_END_DATE");
	}
	
	public static void validateScheduleStartTime(LocalTime startTime) throws CswSchedulerException{
		if(startTime == null) throw new CswSchedulerException("Validator.INVALID_SCHEDULE_TIME");;
	}
	
	public static void validateScheduleDuration(Integer duration) throws CswSchedulerException{
		if(duration<0) throw new CswSchedulerException("Validator.INVALID_SCHEDULE_DURATION");
	}
	
	public static void validateScheduleRepeat(Boolean repeat) throws CswSchedulerException{
		if(repeat == null) throw new CswSchedulerException("Validator.INVALID_SCHEDULE_REPEAT");;
	}
	
	public static boolean validateScheduleFrequency(ScheduleFrequency scheduleFrequency) throws CswSchedulerException{
		return true;
	}
	
	
	
}

