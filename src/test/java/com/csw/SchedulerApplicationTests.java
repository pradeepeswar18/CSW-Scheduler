package com.csw;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.csw.entity.Employee;
import com.csw.entity.Schedule;
import com.csw.exception.CswSchedulerException;
import com.csw.model.ScheduleFrequency;
import com.csw.repository.EmployeeRepository;
import com.csw.service.SchedulerService;
import com.csw.service.SchedulerServiceImpl;

@SpringBootTest
class SchedulerApplicationTests {
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private SchedulerService schedulerService = new SchedulerServiceImpl();

//	@Test
//	void contextLoads() {
//	}
	
	@Test
	public void getScheduleInvalidEmployeeId() throws CswSchedulerException {
		String employeeId = "brad@";
		CswSchedulerException exception = Assertions.assertThrows(CswSchedulerException.class, () -> 
												schedulerService.getEmployee(employeeId));
		Assertions.assertEquals("Validator.INVALID_EMAIL_ID", exception.getMessage());
	}
	
	@Test
	public void getScheduleValidEmployeeId() throws CswSchedulerException {
		String employeeId = "brad@gmail.com";
		List<Schedule> scheduleList = new ArrayList<>();
		Schedule schedule = new Schedule(LocalDate.of(2021, 9, 1), LocalDate.of(2021, 9, 20), LocalTime.of(11, 0), 
				30, true, ScheduleFrequency.DAILY);
		scheduleList.add(schedule);
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setScheduleList(scheduleList);
		Optional<Employee> optionalEmployee = Optional.of(employee);
		Mockito.when(employeeRepository.findById(employeeId)).thenReturn(optionalEmployee);
		Assertions.assertNotNull(schedulerService.getEmployee(employeeId));
	}
}