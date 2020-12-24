package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class})
@RestController
public class SpringbootAwsSqsExampleApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringbootAwsSqsExampleApplication.class);
	
	
	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;
	
	@Value("${cloud.aws.end-point.uri}")
	private String endPoint;
	
	@GetMapping("/send/{message}")
	public void sendMessageToQueue(@PathVariable String message)
	{
		queueMessagingTemplate.send(endPoint,MessageBuilder.withPayload(message).build());
		
	}
	@SqsListener("awsSNSQueue")
	public void loadMessageFromSQS(String message)
	{
		logger.info("message from SQS Queue {}",message);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootAwsSqsExampleApplication.class, args);
	}

}
