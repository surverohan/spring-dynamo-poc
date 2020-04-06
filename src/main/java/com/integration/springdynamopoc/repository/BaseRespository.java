package com.integration.springdynamopoc.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.integration.springdynamopoc.model.BaseEntity;

public interface BaseRespository {

	IDynamoDBMapper getMapper();

	String getEntityHashKey();

	Optional<String> getEntityRangeKey();

	default Map<String, ExpectedAttributeValue> getExpectation(String entityHashKeyValue, String entityRangeKeyValue) {

		Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
		expectedAttributeValueMap.put(getEntityHashKey(),
				new ExpectedAttributeValue(new AttributeValue().withS(entityHashKeyValue))
						.withComparisonOperator(ComparisonOperator.EQ));
		if (getEntityRangeKey().isPresent()) {
			expectedAttributeValueMap.put(getEntityRangeKey().get(),
					new ExpectedAttributeValue(new AttributeValue().withS(entityRangeKeyValue))
							.withComparisonOperator(ComparisonOperator.EQ));

		}
		return expectedAttributeValueMap;
	}

	public default void saveEntity(BaseEntity entity) {

		// Set expected false for an attribute
		ExpectedAttributeValue expectedAttributeValue = new ExpectedAttributeValue();
		expectedAttributeValue.setExists(Boolean.FALSE);

		// Map the id field to the ExpectedAttributeValue
		Map<String, ExpectedAttributeValue> expectedAttributes = new HashMap<>();
		expectedAttributes.put(getEntityHashKey(), expectedAttributeValue);
		if (getEntityRangeKey().isPresent()) {
			expectedAttributes.put(getEntityRangeKey().get(), expectedAttributeValue);

		}
		// Use the attributes within a DynamoDBSaveExpression
		DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
		saveExpression.setExpected(expectedAttributes);

		getMapper().save(entity, saveExpression);

	}

	public default void updateEntity(BaseEntity entity, String entityHashKeyValue, String entityRangeKeyValue) {
		/*
		 * Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new
		 * HashMap<>(); expectedAttributeValueMap.put(getEntityHashKey(), new
		 * ExpectedAttributeValue(new AttributeValue().withS(entityHashKeyValue))
		 * .withComparisonOperator(ComparisonOperator.EQ)); if
		 * (getEntityRangeKey().isPresent()) {
		 * expectedAttributeValueMap.put(getEntityRangeKey().get(), new
		 * ExpectedAttributeValue(new AttributeValue().withS(entityRangeKeyValue))
		 * .withComparisonOperator(ComparisonOperator.EQ));
		 * 
		 * } DynamoDBSaveExpression saveExpression = new
		 * DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
		 */
		getMapper().save(entity,
				new DynamoDBSaveExpression().withExpected(getExpectation(entityHashKeyValue, entityRangeKeyValue)));
	}

	public default void deleteEntity(BaseEntity entity, String entityHashKeyValue, String entityRangeKeyValue) {
		/*
		 * Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new
		 * HashMap<>(); expectedAttributeValueMap.put(getEntityHashKey(), new
		 * ExpectedAttributeValue(new AttributeValue().withS(entityHashKeyValue))); if
		 * (getEntityRangeKey().isPresent()) {
		 * expectedAttributeValueMap.put(getEntityRangeKey().get(), new
		 * ExpectedAttributeValue(new AttributeValue().withS(entityRangeKeyValue))); }
		 * 
		 * DynamoDBDeleteExpression deleteExpression = new DynamoDBDeleteExpression()
		 * .withExpected(expectedAttributeValueMap );
		 */

		getMapper().delete(entity,
				new DynamoDBDeleteExpression().withExpected(getExpectation(entityHashKeyValue, entityRangeKeyValue)));
	}

}
