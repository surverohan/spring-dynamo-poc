package com.integration.springdynamopoc.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponse {
	private String empId;
	private String empFirstName;
	private String empLastName;
	private int empDepNumber;
	private String empCntNumber;
	private List<AddressResponse> addressResponses;

}
