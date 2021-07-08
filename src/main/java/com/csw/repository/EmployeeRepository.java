package com.csw.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.csw.entity.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, String>{

}

