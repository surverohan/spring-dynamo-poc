package com.integration.springdynamopoc.model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "Employee")

public class Employee extends BaseEntity{
    public final static String ENTITY_HASH_KEY = "EmployeeId";
    public final static String ENTITY_RANGE_KEY = "EmployeeFName";
    
    @DynamoDBHashKey(attributeName = ENTITY_HASH_KEY)
	private String employeeId;
    @DynamoDBRangeKey(attributeName = ENTITY_RANGE_KEY)
	private String employeeFName;
    @DynamoDBAttribute(attributeName = "EmployeeLName")
	private String employeeLName;
   // @DynamoDBAttribute(attributeName = "EmployeedeptNumber")
    @DynamoDBIndexRangeKey(localSecondaryIndexName = "zone-index")
	private int employeedeptNumber;
    @DynamoDBAttribute(attributeName = "EmployeeContactNumber")
	private String employeeContactNumber;
    @DynamoDBAttribute(attributeName = "EmployeeAddresses")
    private List<Address> employerAddressList;
}
