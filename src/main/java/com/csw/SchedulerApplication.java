package com.csw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchedulerApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
//		getEmployee("eswar@gmail.com");
//		addSchedule();
//		getSchedule();
	}
//	
//	public void getEmployee(String employeeId) {
//		try {
//			EmployeeDTO employeeDTO = scheduleService.getEmployee(employeeId);
//			System.out.println(employeeDTO);
//		} catch(Exception e) {
//			LOGGER.error(e.getMessage());
//		}
//	}
	
//	public void addSchedule() {
//		try {
//			Set<ScheduleDTO> scheduleList = new HashSet<>();
//			LocalDate startDate = LocalDate.of(2021, 07, 21);
//			LocalDate endDate = LocalDate.of(2021, 07, 30);
//			LocalTime time = LocalTime.of(11, 0);
//			Integer duration = 30;
//			Boolean repeat = true;
//			ScheduleFrequency scheduleFrequency = ScheduleFrequency.DAILY;
//			ScheduleDTO scheduleDTO = new ScheduleDTO(startDate, endDate, time, duration, repeat, scheduleFrequency);
//			scheduleList.add(scheduleDTO);
//			EmployeeDTO employeeDTO = new EmployeeDTO("eswar@gmail.com", scheduleList);
//			
//			scheduleService.addSchedule(employeeDTO);
//		} catch(Exception e) {
//			LOGGER.error(e.getMessage());
//		}
//	}
	
//	public void getSchedule() {
//		try {
//			List<ScheduleTriggerDTO> scheduleTriggerDTOList = scheduleService.getSchedule("2021-07-01");
//			for(ScheduleTriggerDTO scheduleTriggerDTO: scheduleTriggerDTOList) {
//				System.out.println("Date: " + scheduleTriggerDTO.getDate() + " Time: " + scheduleTriggerDTO.getTime() + 
//						" Duration: " + scheduleTriggerDTO.getDuration() + " mins \n");
//			}
//		} catch(Exception e) {
//			LOGGER.error(e.getMessage());
//		}
//	}
}
