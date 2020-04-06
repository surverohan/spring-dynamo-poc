package com.integration.springdynamopoc.service;

import com.integration.springdynamopoc.model.EmployeeRequest;
import com.integration.springdynamopoc.model.EmployeeResponse;

public interface EmployeeService {
	
	public EmployeeResponse createEmployee(EmployeeRequest employeeRequest);
    public EmployeeResponse readEmployee(String employeeId,String employeeFName);
    public EmployeeResponse updateEmployee(EmployeeRequest employeeRequest) ;
    public void deleteEmployee(String employeeId,String employeeFName) ;

}
