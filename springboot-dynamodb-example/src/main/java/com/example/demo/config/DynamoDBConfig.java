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
	private static final String SECRET_KEY = "eBAFkVOMgNLPQelJNPpcVq/cGgrnUJRdFT2wP6iy";
	private static final String ACCESS_KEY = "AKIAV2RF7IVORNYA3HXW";
	private static final String REGION = "us-east-2";
	private static final String SERVICE_ENDPOINT = "dynamodb.us-east-2.amazonaws.com";
	
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
