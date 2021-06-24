package com.csw.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csw.entity.Employee;
import com.csw.entity.Schedule;
import com.csw.entity.ScheduleTrigger;
import com.csw.exception.CswSchedulerException;
import com.csw.model.EmployeeDTO;
import com.csw.model.ScheduleDTO;
import com.csw.model.ScheduleFrequency;
import com.csw.model.ScheduleTriggerDTO;
import com.csw.repository.SchedulerRepository;
import com.csw.validator.Validator;

@Service
@Transactional
public class SchedulerServiceImpl implements SchedulerService {
	
	@Autowired
	private SchedulerRepository scheduleRepository;
	
	private List<ScheduleTrigger> getTriggers(Schedule schedule, String employeeId) {
		List<ScheduleTrigger>  triggers = new ArrayList<>();
		LocalDate date = schedule.getStartDate(), endDate = schedule.getEndDate();
		
		while(!date.isAfter(endDate)) {
			if(!(date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || (date.getDayOfWeek().equals(DayOfWeek.SUNDAY)))) {
				ScheduleTrigger trigger = new ScheduleTrigger();
				trigger.setDate(date);
				trigger.setDuration(schedule.getDuration());
				trigger.setTime(schedule.getTime());
				trigger.setEmployeeId(employeeId);
				triggers.add(trigger);
			}
			date = date.plusDays(1);
		}
		
		return triggers;
	}
	
	private List<ScheduleTrigger> getTriggers(Schedule schedule, String employeeId, Integer increment) {
		List<ScheduleTrigger>  triggers = new ArrayList<>();
		LocalDate date = schedule.getStartDate(), endDate = schedule.getEndDate();
		
		while(!date.isAfter(endDate)) {
			ScheduleTrigger trigger = new ScheduleTrigger();
			trigger.setDate(date);
			trigger.setDuration(schedule.getDuration());
			trigger.setTime(schedule.getTime());
			trigger.setEmployeeId(employeeId);
			triggers.add(trigger);
			date = date.plusDays(increment);
		}
		
		return triggers;
	}
	
	private List<ScheduleTrigger> getMonthlyTriggers(Schedule schedule, String employeeId) {
		List<ScheduleTrigger>  triggers = new ArrayList<>();
		LocalDate date = schedule.getStartDate(), endDate = schedule.getEndDate();
		
		while(!date.isAfter(endDate)) {
			ScheduleTrigger trigger = new ScheduleTrigger();
			trigger.setDate(date);
			trigger.setDuration(schedule.getDuration());
			trigger.setTime(schedule.getTime());
			trigger.setEmployeeId(employeeId);
			triggers.add(trigger);
			date = date.plusMonths(1);
		}
		
		return triggers;
	}

	
	@Override
	public void addSchedule(EmployeeDTO employeeDTO) throws CswSchedulerException {
		
		Validator.validate(employeeDTO);
				
		//Employee existingEmployee = scheduleRepository.getEmployee(employeeDTO.getEmployeeId());
		List<Schedule> scheduleList = new ArrayList<>();
		Set<ScheduleDTO> scheduleDTOList = employeeDTO.getScheduleList();
		List<ScheduleTrigger> scheduleTriggerList = new ArrayList<>();
		
		for(ScheduleDTO scheduleDTO: scheduleDTOList) {
			Schedule schedule = new Schedule(scheduleDTO.getStartDate(), scheduleDTO.getEndDate(), scheduleDTO.getTime(), 
					scheduleDTO.getDuration(), scheduleDTO.getRepeat(), scheduleDTO.getScheduleFrequency());
			
			boolean repeat = schedule.getRepeat();
			ScheduleFrequency scheduleFrequency = schedule.getScheduleFrequency();
			List<ScheduleTrigger> triggers = null;
			if(repeat) {
				switch(scheduleFrequency) {
				case WEEKDAYS:
					triggers = getTriggers(schedule, employeeDTO.getEmployeeId());
					break;
				
				case DAILY:
					triggers = getTriggers(schedule, employeeDTO.getEmployeeId(),1);
					break;
								
				case WEEKLY:
					triggers = getTriggers(schedule, employeeDTO.getEmployeeId(), 7);
					break;
					
				case MONTHLY:
					triggers = getMonthlyTriggers(schedule, employeeDTO.getEmployeeId());
					break;
				
				default:
					break;
				}
				
			} else {
				int increment = (int) (schedule.getStartDate().until(schedule.getEndDate(), ChronoUnit.DAYS)) + 2;
				triggers = getTriggers(schedule, employeeDTO.getEmployeeId(), increment);
			}
			
			scheduleTriggerList.addAll(triggers);
			schedule.setSchedulerTriggerList(triggers);
			scheduleList.add(schedule);
		}
		
		Employee employee = new Employee(employeeDTO.getEmployeeId(), scheduleList); 
		
		if(scheduleRepository.isExistingEmployee(employeeDTO.getEmployeeId())) {
			scheduleRepository.addSchedule(employee);
		} else {
			scheduleRepository.createSchedule(employee);
		}
	}
	
	@Override
	public EmployeeDTO getEmployee(String employeeId) throws CswSchedulerException {
		Validator.validateEmployeeId(employeeId);
		
		Employee employee  = scheduleRepository.getEmployee(employeeId);
		if(employee==null ) {
			throw new CswSchedulerException("Service.EMPLOYEE_NOT_PRESENT");
		}
		EmployeeDTO employeeDTO = null;
		
		Set<ScheduleDTO> scheduleDTOList = new HashSet<>();
		List<Schedule> scheduleList = employee.getScheduleList();
		
		for(Schedule schedule: scheduleList) {
			ScheduleDTO scheduleDTO = new ScheduleDTO(schedule.getStartDate(), schedule.getEndDate(), 
					schedule.getTime(), schedule.getDuration(), schedule.getRepeat(), schedule.getScheduleFrequency().toString());
			scheduleDTOList.add(scheduleDTO);
		}
		employeeDTO = new EmployeeDTO(employeeId, scheduleDTOList);
		
		return employeeDTO;
	}
	
	@Override
	public List<ScheduleTriggerDTO> getSchedule(String dateString) throws CswSchedulerException {
		LocalDate date = LocalDate.parse(dateString);
		List<ScheduleTriggerDTO> scheduleTriggerDTOList = null;
		List<ScheduleTrigger> scheduleTriggerList;
		if(date != null) {
			scheduleTriggerList = scheduleRepository.getSchedule(date);
			scheduleTriggerDTOList = new ArrayList<>();
			for(ScheduleTrigger scheduleTrigger: scheduleTriggerList) {
				ScheduleTriggerDTO scheduleTriggerDTO = new ScheduleTriggerDTO(scheduleTrigger.getEmployeeId(), scheduleTrigger.getDate(),
						scheduleTrigger.getTime(), scheduleTrigger.getDuration());
				scheduleTriggerDTOList.add(scheduleTriggerDTO);
			}	
		}
		return scheduleTriggerDTOList;
	}
	
	@Override
	public void updateSchedule(EmployeeDTO employeeDTO) throws CswSchedulerException {
		Validator.validate(employeeDTO);
		
		if(!scheduleRepository.isExistingEmployee(employeeDTO.getEmployeeId())) {
			throw new CswSchedulerException("Service.EMPLOYEE_NOT_PRESENT");
		}
		
		scheduleRepository.updateSchedule(employeeDTO);
	}
	
	@Override
	public Integer cancelSchedule(String employeeId, String startDateString, String endDateString, String timeString) 
			throws CswSchedulerException {
		LocalDate startDate = LocalDate.parse(startDateString);
		LocalDate endDate = LocalDate.parse(endDateString);
		LocalTime time = LocalTime.parse(timeString);
		
		Validator.validateScheduleDate(startDate, endDate);
		Validator.validateScheduleStartTime(time);
		
		if(!scheduleRepository.isExistingEmployee(employeeId)) {
			throw new CswSchedulerException("Service.EMPLOYEE_NOT_PRESENT");
		}
		
		return scheduleRepository.cancelSchedule(employeeId, startDate, endDate, time);
	}
	
	@Override
	public Integer cancelSchedule(String employeeId, String startDateString, String endDateString) 
			throws CswSchedulerException {
		LocalDate startDate = LocalDate.parse(startDateString);
		LocalDate endDate = LocalDate.parse(endDateString);
		Validator.validateEmployeeId(employeeId);
		Validator.validateScheduleDate(startDate, endDate);
		
		if(!scheduleRepository.isExistingEmployee(employeeId)) {
			throw new CswSchedulerException("Service.EMPLOYEE_NOT_PRESENT");
		}
		
		return scheduleRepository.cancelSchedule(employeeId,startDate, endDate);
	}
	
	@Override
	public Integer cancelSchedule(String employeeId) throws CswSchedulerException {
		Validator.validateEmployeeId(employeeId);
		Employee employee = scheduleRepository.getEmployee(employeeId);
		
		if(employee == null ) {
			throw new CswSchedulerException("Service.EMPLOYEE_NOT_PRESENT");
		}
		
		Integer scheduleCount = employee.getScheduleList().size();
		scheduleRepository.cancelSchedule(employee);
		
		return scheduleCount;
	}
	
	@Override
	public Integer cancelScheduleByDate(String employeeId, String dateString) throws CswSchedulerException {
		LocalDate date = LocalDate.parse(dateString);
		
		if(!scheduleRepository.isExistingEmployee(employeeId)) {
			throw new CswSchedulerException("Service.EMPLOYEE_NOT_PRESENT");
		}
		
		return scheduleRepository.cancelScheduleByDate(employeeId,date);
	}
	
	@Override
	public Integer cancelScheduleByDate(String employeeId, String dateString, String timeString) throws CswSchedulerException {
		LocalDate date = LocalDate.parse(dateString);
		LocalTime time = LocalTime.parse(timeString);
		
		if(!scheduleRepository.isExistingEmployee(employeeId)) {
			throw new CswSchedulerException("Service.EMPLOYEE_NOT_PRESENT");
		}
		
		return scheduleRepository.cancelScheduleByDate(employeeId, date, time);
	}
}

