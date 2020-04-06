package com.integration.springdynamopoc.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.integration.springdynamopoc.model.Address;
import com.integration.springdynamopoc.model.AddressRequest;
import com.integration.springdynamopoc.model.AddressResponse;
import com.integration.springdynamopoc.model.BaseEntity;
import com.integration.springdynamopoc.model.Employee;
import com.integration.springdynamopoc.model.EmployeeRequest;
import com.integration.springdynamopoc.model.EmployeeResponse;

@Mapper(componentModel = "spring") // Creates a Spring Bean automatically
public interface EmployeeMapper {

	@Mappings({ @Mapping(target = "employeeId", source = "employeeRequest.emp_Id"),
			@Mapping(target = "employeeFName", source = "employeeRequest.emp_First_Name"),
			@Mapping(target = "employeeLName", source = "employeeRequest.emp_Last_Name"),
			@Mapping(target = "employeedeptNumber", source = "employeeRequest.emp_Dep_Number"),
			@Mapping(target = "employeeContactNumber", source = "employeeRequest.emp_Cnt_Number") })
	Employee toEmployee(EmployeeRequest employeeRequest);

	@AfterMapping
	 public default void populateEmployee(@MappingTarget Employee toEmployee) {
		 System.out.println("After mappping........");
		BaseEntity model= (BaseEntity)toEmployee;
		model.setCreatedAt(System.currentTimeMillis());
		model.setCreatedBy("createdBy");
	    }

	@Mappings({ @Mapping(target = "empId", source = "employee.employeeId"),
			@Mapping(target = "empFirstName", source = "employee.employeeFName"),
			@Mapping(target = "empLastName", source = "employee.employeeLName"),
			@Mapping(target = "empDepNumber", source = "employee.employeedeptNumber"),
			@Mapping(target = "empCntNumber", source = "employee.employeeContactNumber") })
	EmployeeResponse fromEmployee(Employee employee);

	@Mappings({ @Mapping(target = "city", source = "addressRequest.emp_City"),
			@Mapping(target = "zipCode", source = "addressRequest.emp_ZipCode") })
	Address toAddress(AddressRequest addressRequest);

	List<Address> toAddresses(Collection<AddressRequest> addressRequests);

	@Mappings({ @Mapping(target = "empCity", source = "address.city"),
			@Mapping(target = "empZipCode", source = "address.zipCode") })
	AddressResponse fromAddress(Address address);

	List<AddressResponse> fromAddresses(Collection<Address> addresses);

}
