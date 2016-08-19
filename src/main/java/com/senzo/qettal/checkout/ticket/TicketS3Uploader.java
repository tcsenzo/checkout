package com.senzo.qettal.checkout.ticket;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Component
public class TicketS3Uploader {
	
	@Value("${aws.s3.bucket.tickets}")
	private String ticketsS3Bucket;
	@Autowired
	private AmazonS3 s3;
	
	TicketQRCode upload(TicketQRCode qrCode){
		ObjectMetadata meta = new ObjectMetadata();
		byte[] buffer = qrCode.getBytes();
		meta.setContentLength(buffer.length);
		meta.setContentType("image/png");
		ByteArrayInputStream is = new ByteArrayInputStream(buffer);
		
		try {
			String hashedTicketName = qrCode.getSecurityHash();
			String hashedKey = hashedTicketName +".png";
			PutObjectRequest request = new PutObjectRequest(ticketsS3Bucket, hashedKey, is, meta);
			AccessControlList acl = new AccessControlList();
			acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
			request.setAccessControlList(acl);
			s3.putObject(request);
			return qrCode;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
