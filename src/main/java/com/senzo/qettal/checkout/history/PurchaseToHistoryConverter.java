package com.senzo.qettal.checkout.history;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senzo.qettal.checkout.purchase.Purchase;
import com.senzo.qettal.checkout.purchase.PurchaseItem;
import com.senzo.qettal.checkout.ticket.TicketQRCodeUrlExtractor;

@Component
public class PurchaseToHistoryConverter {

	@Autowired
	private TicketQRCodeUrlExtractor urlExtractor;
	
	public PurchaseToHistoryDTO convert(Purchase purchase) {
		List<TicketsToHistoryDTO> items = purchase.getItems()
					.stream()
					.filter(PurchaseItem::hasTicket)
					.map(i -> new TicketsToHistoryDTO(urlExtractor.extractFrom(i.getTicket().get()), i.getType(), i.getPrice()))
					.collect(toList());
		
		EventToHistoryDTO event = new EventToHistoryDTO(purchase.getEventName(), purchase.getScheduledDate());
		return new PurchaseToHistoryDTO(purchase.getId(), items, event);
	}
}
