package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDBConfig {
	private static final String SECRET_KEY = "";
	private static final String ACCESS_KEY = "";
	private static final String REGION = "";
	private static final String SERVICE_ENDPOINT = "";

	@Bean
	public DynamoDBMapper mapper() {
		return new DynamoDBMapper(awsDynamoDBConfig());
	}

	private AmazonDynamoDB awsDynamoDBConfig() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(
						new AwsClientBuilder.EndpointConfiguration(SERVICE_ENDPOINT, REGION))
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
				.build();
	}

}
