package com.integration.springdynamopoc.service;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.integration.springdynamopoc.mapper.AbstractEmpMapper;
import com.integration.springdynamopoc.mapper.EmployeeMapper;
import com.integration.springdynamopoc.model.BaseEntity;
import com.integration.springdynamopoc.model.Employee;
import com.integration.springdynamopoc.model.EmployeeRequest;
import com.integration.springdynamopoc.model.EmployeeResponse;
import com.integration.springdynamopoc.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	private EmployeeMapper employeeMapper;
	
	private AbstractEmpMapper empMapper;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository,EmployeeMapper employeeMapper,AbstractEmpMapper empMapper) {
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;

		this.empMapper = empMapper;
	}

	@Override
	public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
		Employee employee = empMapper.toEmployee( employeeRequest);
		employee.setEmployerAddressList(empMapper.toAddresses( employeeRequest.getAddressRequests()));
		
		BaseEntity model= (BaseEntity)employee;
		model.setUpdatedAt(System.currentTimeMillis());
		model.setUpdatedBy("updatedByTEST");
		model.setCreatedAt(System.currentTimeMillis());
		model.setCreatedBy("createdByTEST");
		
		EmployeeResponse response = employeeMapper.fromEmployee (employeeRepository.createEmployee(employee));
		response.setAddressResponses(employeeMapper.fromAddresses(employee.getEmployerAddressList()));
		return response;
	}

	@Override
	public EmployeeResponse readEmployee(String employeeId,String employeeFName) {
		Employee employee = employeeRepository.readEmployee(employeeId,employeeFName);

		EmployeeResponse response = employeeMapper.fromEmployee (employee);
		response.setAddressResponses(employeeMapper.fromAddresses(employee.getEmployerAddressList()));
		return response;
	}

	@Override
	public EmployeeResponse updateEmployee(EmployeeRequest employeeRequest) {
		
		Employee employee = employeeMapper.toEmployee( employeeRequest);
		employee.setEmployerAddressList(employeeMapper.toAddresses( employeeRequest.getAddressRequests()));
		
		Employee updatedEmployee = employeeRepository.updateEmployee(employee);
		EmployeeResponse response = employeeMapper.fromEmployee (updatedEmployee);
		response.setAddressResponses(employeeMapper.fromAddresses(updatedEmployee.getEmployerAddressList()));
		return response;
	}

	@Override
	public void deleteEmployee(String employeeId ,String employeeFName) {
		employeeRepository.deleteEmployee(employeeId,employeeFName);

	}

}
