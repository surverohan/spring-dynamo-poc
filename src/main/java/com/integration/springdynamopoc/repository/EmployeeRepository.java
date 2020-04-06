package com.integration.springdynamopoc.repository;

import com.integration.springdynamopoc.model.Employee;

public interface EmployeeRepository extends BaseRespository{
	
	public Employee createEmployee(Employee employee);
    public Employee readEmployee(String employeeId,String employeeFName);
    public Employee updateEmployee(Employee employee) ;
    public void deleteEmployee(String employeeId,String employeeFName) ;
}
