package com.integration.springdynamopoc.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import lombok.Getter;
import lombok.Setter;
@DynamoDBDocument
@Getter
@Setter
public class Address {
    @DynamoDBAttribute(attributeName = "EmployeeCity")
	private String city;
    @DynamoDBAttribute(attributeName = "EmployeeZipCode")
  	private int zipCode;
}
