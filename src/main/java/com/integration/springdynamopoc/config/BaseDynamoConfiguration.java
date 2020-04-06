package com.integration.springdynamopoc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "dynamo")
public class BaseDynamoConfiguration {

	private String awsAccessKey;

	private String awsSecretKey;

	private String awsRegion=Regions.US_EAST_1.getName();

	private String awsDynamoDBEndPoint;

	@Bean
	public DynamoDBMapper mapper() {
		return new DynamoDBMapper(amazonDynamoDBConfig());
	}

	private AmazonDynamoDB amazonDynamoDBConfig() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoDBEndPoint, awsRegion))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
				.build();
	}

	/*
	 * @Bean public DynamoDbAsyncClient dynamoDbAsyncClient() { return
	 * DynamoDbAsyncClient.builder()
	 * .endpointOverride(URI.create(amazonDynamoEndpoint))
	 * .credentialsProvider(awsCredentialsProvider()) .region(Region.US_WEST_2) //
	 * .httpClient() .build(); }
	 * 
	 * @Bean public AwsCredentialsProvider awsCredentialsProvider(){ return
	 * StaticCredentialsProvider.create(
	 * AwsBasicCredentials.create(amazonAWSAccessKey,amazonAWSSecretKey)); }
	 * 
	 * @Bean public DynamoDbClient dynamoDbClient() { return
	 * DynamoDbClient.builder() .endpointOverride(URI.create(amazonDynamoEndpoint))
	 * .credentialsProvider(awsCredentialsProvider()) .region(Region.US_WEST_2)
	 * .build(); }
	 */
}
