package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;

@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class,ContextRegionProviderAutoConfiguration.class})
@RestController
public class SpringbootAwsSnsExampleApplication {

	@Autowired
	private AmazonSNSClient snsClient;
	
	String TOPIC_ARN = "arn:aws:sns:us-east-2:400585344349:awsTopic";
	
	@GetMapping("/addSubscription/{emailId}")
	public String addSubscription(@PathVariable String emailId)
	{
		SubscribeRequest request = new SubscribeRequest(TOPIC_ARN,"email",emailId);
		snsClient.subscribe(request);
		
		return "Subscription request is pending. To confirm the subscription, check your email:"+emailId;
		
	}
	@GetMapping("/sendNotification")
	public String publishMessageToTopic()
	{
		PublishRequest publishRequest = new PublishRequest(TOPIC_ARN,buildEmailBody(),"Notifcation : Network Connectivity Issue");
		snsClient.publish(publishRequest);
		return "Notifcation sent";
		
	}
	
	private String buildEmailBody()
	{
		return "Dear Employee,\n "
				+ "\n"
				+ "\n"+
				"Connection Down Noida. \n";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootAwsSnsExampleApplication.class, args);
	}

}
