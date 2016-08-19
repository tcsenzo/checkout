package com.senzo.qettal.checkout.ticket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TicketQRCodeUrlExtractor {

	@Value("${aws.s3.url}")
	private String amazonS3Url;
	@Value("${aws.s3.bucket.tickets}")
	private String amazonS3TicketsBucket;
	
	public String extractFrom(Ticket ticket) {
		return String.format("http://%s.%s/%s.png", amazonS3TicketsBucket, amazonS3Url, ticket.getHash());
	}

}
