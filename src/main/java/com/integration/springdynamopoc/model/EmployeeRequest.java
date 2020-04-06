package com.integration.springdynamopoc.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
	private String emp_Id;
	private String emp_First_Name;
	private String emp_Last_Name;
	private int emp_Dep_Number;
	private String emp_Cnt_Number;
	private List<AddressRequest> addressRequests;
}
