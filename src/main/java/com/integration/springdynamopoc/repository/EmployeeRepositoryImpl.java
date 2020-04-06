package com.integration.springdynamopoc.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper;
import com.integration.springdynamopoc.model.Employee;

import lombok.Getter;

@Repository
@Getter
public class EmployeeRepositoryImpl implements EmployeeRepository {

	private IDynamoDBMapper mapper;
	private String entityHashKey = Employee.ENTITY_HASH_KEY;
	private Optional<String> entityRangeKey = Optional.of(Employee.ENTITY_HASH_KEY);

	public EmployeeRepositoryImpl(DynamoDBMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Employee createEmployee(Employee employee) {
		this.saveEntity(employee);
		return employee;
	}

	@Override
	public Employee readEmployee(String employeeId, String employeeFName) {
		return mapper.load(Employee.class, employeeId, employeeFName);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		this.updateEntity(employee, employee.getEmployeeId(), employee.getEmployeeFName());
		return employee;
	}

	@Override
	public void deleteEmployee(String employeeId, String employeeFName) {
		Employee employee = Employee.builder().employeeId(employeeId).employeeFName(employeeFName).build();
		this.deleteEntity(employee, employee.getEmployeeId(), employee.getEmployeeFName());
	}

}
