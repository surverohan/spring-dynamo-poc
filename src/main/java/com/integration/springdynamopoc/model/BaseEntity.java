package com.integration.springdynamopoc.model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
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
@DynamoDBDocument
public class BaseEntity {

	@DynamoDBAttribute(attributeName = "createdBy")
	private String createdBy;

	@DynamoDBAttribute(attributeName = "createdAt")
	private Long createdAt;

	@DynamoDBAttribute(attributeName = "updatedBy")
	private String updatedBy;

	@DynamoDBAttribute(attributeName = "updatedAt")
	private Long updatedAt;

	@DynamoDBVersionAttribute(attributeName = "verionNo")
	private Long version;

}
