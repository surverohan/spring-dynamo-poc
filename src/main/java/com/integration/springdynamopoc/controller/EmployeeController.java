package com.integration.springdynamopoc.controller;

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
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.integration.springdynamopoc.model.EmployeeRequest;
import com.integration.springdynamopoc.model.EmployeeResponse;
import com.integration.springdynamopoc.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping
	public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
		try {
			EmployeeResponse response = employeeService.createEmployee(employeeRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (AmazonServiceException e) {
			log.error(" createEmployee : "+e.getErrorMessage());
			throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
		} catch (AmazonClientException e) {
			log.error(" createEmployee : "+e.getMessage());

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}

	@GetMapping(value = "/{employeeId}/{employeeFName}")
	public ResponseEntity<EmployeeResponse> readEmployee(@PathVariable String employeeId, @PathVariable String employeeFName) {
		try {
			EmployeeResponse response = employeeService.readEmployee(employeeId,employeeFName);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (AmazonServiceException e) {
			log.error(" readEmployee : "+e.getErrorMessage());

			throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
		} catch (AmazonClientException e) {
			log.error(" readEmployee : "+e.getMessage());

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}

	@PutMapping
	public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody EmployeeRequest employeeRequest) {
		try {
			EmployeeResponse response = employeeService.updateEmployee(employeeRequest);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (AmazonServiceException e) {
			log.error(" updateEmployee : "+e.getErrorMessage());

			throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
		} catch (AmazonClientException e) {
			log.error(" updateEmployee : "+e.getMessage());

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}

	@DeleteMapping(value = "/{employeeId}/{employeeFName}")
	public ResponseEntity deleteEmployee(@PathVariable String employeeId, @PathVariable String employeeFName) {
		try {
			employeeService.deleteEmployee(employeeId,employeeFName);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (AmazonServiceException e) {
			log.error(" deleteEmployee : "+e.getErrorMessage());

			throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
		} catch (AmazonClientException e) {
			log.error(" deleteEmployee : "+e.getMessage());

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}

}
