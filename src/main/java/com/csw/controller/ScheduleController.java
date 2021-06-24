package com.csw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csw.exception.CswSchedulerException;
import com.csw.model.EmployeeDTO;
import com.csw.model.ScheduleTriggerDTO;
import com.csw.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	private Environment environment;

	@Autowired
	private ScheduleService schedulerService;
	
	@GetMapping(value = "/get/employee/{employeeId}")
	public ResponseEntity<EmployeeDTO> getEmployee( @PathVariable String employeeId) throws CswSchedulerException {
		EmployeeDTO employeeDTO = schedulerService.getEmployee(employeeId);
		return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get/schedule/{dateString}")
	public ResponseEntity<List<ScheduleTriggerDTO>> getSchedule(@PathVariable String dateString) throws CswSchedulerException{
		List<ScheduleTriggerDTO> scheduleTriggerDTO = schedulerService.getSchedule(dateString);
		return new ResponseEntity<>(scheduleTriggerDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> addSchedule(@RequestBody EmployeeDTO employeeDTO) throws CswSchedulerException {
		schedulerService.addSchedule(employeeDTO);
		String successMessage = environment.getProperty("Controller.INSERT_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/update/time")
	public ResponseEntity<String> updateSchedule(@RequestBody EmployeeDTO employeeDTO) throws CswSchedulerException {
		schedulerService.updateSchedule(employeeDTO);
		String successMessage = environment.getProperty("Controller.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/cancel/{employeeId}/{startDateString}/{endDateString}")
	public ResponseEntity<String> cancelSchedule(@PathVariable String employeeId, @PathVariable String startDateString, 
			@PathVariable String endDateString) throws CswSchedulerException {
		Integer count = schedulerService.cancelSchedule(employeeId,startDateString, endDateString);
		String successMessage = environment.getProperty("Controller.DELETE_SUCCESS") + " " + count + " schedules";
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/cancel/{employeeId}/{startDateString}/{endDateString}/{timeString}")
	public ResponseEntity<String> cancelSchedule(@PathVariable String employeeId, @PathVariable String startDateString, 
			@PathVariable String endDateString, @PathVariable String timeString) 
			throws CswSchedulerException {
		Integer count = schedulerService.cancelSchedule(employeeId,startDateString, endDateString, timeString);
		String successMessage = environment.getProperty("Controller.DELETE_SUCCESS") + " " + count + " schedules";
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/cancel/employee/{employeeId}")
	public ResponseEntity<String> cancelAllSchedules(@PathVariable String employeeId) throws CswSchedulerException {
		Integer count = schedulerService.cancelSchedule(employeeId);
		String successMessage = environment.getProperty("Controller.DELETE_SUCCESS") + " " + count + 
								" schedules for " + employeeId;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/cancel/date/{employeeId}/{date}")
	public ResponseEntity<String> cancelScheduleByDate(@PathVariable String employeeId, @PathVariable String date) 
			throws CswSchedulerException {
		Integer count = schedulerService.cancelScheduleByDate(employeeId, date);
		String successMessage = environment.getProperty("Controller.DELETE_SUCCESS") + " " + count + 
								" schedules for " + date;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/cancel/date/{employeeId}/{date}/{time}")
	public ResponseEntity<String> cancelScheduleByDate(@PathVariable String employeeId, @PathVariable String date, 
			@PathVariable String time) throws CswSchedulerException {
		Integer count = schedulerService.cancelScheduleByDate(employeeId, date, time);
		String successMessage = environment.getProperty("Controller.DELETE_SUCCESS") + " " + count + 
								" schedules for " + date;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
}

