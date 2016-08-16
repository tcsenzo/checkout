package com.senzo.qettal.checkout.amazon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

@Configuration
public class AmazonS3Producer {

	@Value("${cloud.aws.credentials.accessKey}")
	private String accessKey;
	@Value("${cloud.aws.credentials.secretKey}")
	private String secretKey;
	@Value("${cloud.aws.credentials.instanceProfile}")
	private String instanceProfile;
	@Value("${cloud.aws.region.static}")
	private String region;
	
	@Bean
	public AmazonS3 getS3(){
		AmazonS3Client amazonS3Client;
		if(Boolean.getBoolean(instanceProfile)){
			amazonS3Client = new AmazonS3Client(new InstanceProfileCredentialsProvider());
		} else {
			amazonS3Client = new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey));
		}
		return amazonS3Client;
	}
}
