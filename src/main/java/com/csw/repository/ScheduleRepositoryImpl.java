package com.csw.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.csw.entity.Employee;
import com.csw.entity.ScheduleTrigger;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Employee getEmployee(String employeeId){
		return entityManager.find(Employee.class, employeeId);
	}
	
	@Override
	public boolean isExistingEmployee(String employeeId) {
		String queryString = "select e.employeeId from Employee e where e.employeeId = :employeeId";
		Query query = entityManager.createQuery(queryString);
		query.setParameter("employeeId", employeeId);
		return query.getResultList().size()>0;
	}
	
	@Override
	public void addSchedule(Employee employee) {
		Employee existingEmployee = entityManager.find(Employee.class, employee.getEmployeeId());
		existingEmployee.addSchedule(employee.getScheduleList());
	}
	
	@Override
	public void addScheduleTriggers(List<ScheduleTrigger> scheduleTriggerList) {
		for(ScheduleTrigger trigger: scheduleTriggerList) {
			entityManager.persist(trigger);
		}
	}
	
	@Override
	public void createSchedule(Employee employee) {
		entityManager.persist(employee);
	}
	
	@Override
	public List<ScheduleTrigger> getSchedule(LocalDate date) {
		String queryString = "select s from ScheduleTrigger s where s.date = :date";
		Query query = entityManager.createQuery(queryString);
		query.setParameter("date", date);
		List<ScheduleTrigger> scheduleTriggerList = query.getResultList();
		return scheduleTriggerList;
	}
	
	@Override
	public Integer cancelSchedule(String employeeId, LocalDate startDate, LocalDate endDate, LocalTime time) {
		String queryString = "delete from Schedule s where s.employeeId = :employeeId AND s.startDate = :startDate "
				+ "AND s.endDate = :endDate AND s.time = :time";
		Query query = entityManager.createQuery(queryString);
		query.setParameter("employeeId", employeeId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setParameter("time", time);
		return query.executeUpdate();
	}
	
	@Override
	public Integer cancelSchedule(String employeeId, LocalDate startDate, LocalDate endDate) {
		String queryString = "delete from Schedule s where s.employeeId = :employeeId AND s.startDate = :startDate "
				+ "AND s.endDate = :endDate";
		Query query = entityManager.createQuery(queryString);
		query.setParameter("employeeId", employeeId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.executeUpdate();
	}
	
	@Override
	public void cancelSchedule(Employee employee) {
		entityManager.remove(employee);
	}
	
	@Override
	public Integer cancelScheduleByDate(String employeeId, LocalDate date) {
		String queryString = "delete from ScheduleTrigger s where s.employeeId = :employeeId AND s.date = :date";
		Query query = entityManager.createQuery(queryString);
		query.setParameter("employeeId", employeeId);
		query.setParameter("date", date);
		return query.executeUpdate();
	}
	
	@Override
	public Integer cancelScheduleByDate(String employeeId, LocalDate date, LocalTime time) {
		String queryString = "delete from ScheduleTrigger s where s.employeeId = :employeeId AND s.date = :date "
				+ "AND time= :time";
		Query query = entityManager.createQuery(queryString);
		query.setParameter("employeeId", employeeId);
		query.setParameter("date", date);
		query.setParameter("time", time);
		return query.executeUpdate();
	}
}
