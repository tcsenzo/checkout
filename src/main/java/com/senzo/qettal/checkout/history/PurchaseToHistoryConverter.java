package com.senzo.qettal.checkout.history;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senzo.qettal.checkout.address.AddressDTO;
import com.senzo.qettal.checkout.payment.Payment;
import com.senzo.qettal.checkout.purchase.Purchase;
import com.senzo.qettal.checkout.purchase.PurchaseItem;
import com.senzo.qettal.checkout.purchase.PurchaseItemDTO;
import com.senzo.qettal.checkout.purchase.PurchaseItemDTOConverter;
import com.senzo.qettal.checkout.ticket.TicketQRCodeUrlExtractor;

@Component
public class PurchaseToHistoryConverter {

	@Autowired
	private TicketQRCodeUrlExtractor urlExtractor;
	@Autowired
	private PurchaseItemDTOConverter itemConverter;
	
	public PurchaseToHistoryDTO convert(Purchase purchase) {
		List<TicketsToHistoryDTO> items = purchase.getItems()
					.stream()
					.filter(PurchaseItem::hasTicket)
					.map(i -> new TicketsToHistoryDTO(urlExtractor.extractFrom(i.getTicket().get()), i.getType(), i.getPrice()))
					.collect(toList());
		
		
		AddressDTO address = AddressDTO.from(purchase.getTheaterAddress());
		TheaterDTO theater = new TheaterDTO(purchase.getTheaterName(), address);
		EventToHistoryDTO event = new EventToHistoryDTO(purchase.getEventName(), purchase.getEventImage(), purchase.getScheduledDate(), theater);
		
		PurchaseToHistoryDTO purchaseToHistoryDTO = new PurchaseToHistoryDTO(purchase.getId(), purchase.getCreatedAt(), items, event);
		Optional<Payment> optionalPayment = purchase.getPayment();
		if(optionalPayment.isPresent()) {
			purchaseToHistoryDTO.setPaymentStatus(optionalPayment.get().getLastStatus());
		}
		return purchaseToHistoryDTO;
	}
	
	public TheaterPurchaseToHistoryDTO convertForTheater(Purchase purchase) {
		AddressDTO address = AddressDTO.from(purchase.getTheaterAddress());
		TheaterDTO theater = new TheaterDTO(purchase.getTheaterName(), address);
		EventToHistoryDTO event = new EventToHistoryDTO(purchase.getEventName(), purchase.getEventImage(), purchase.getScheduledDate(), theater);
		
		List<PurchaseItemDTO> items = itemConverter.convert(purchase);
		TheaterPurchaseToHistoryDTO purchaseToHistoryDTO = new TheaterPurchaseToHistoryDTO(purchase.getId(), purchase.getCreatedAt(), items, event);
		Optional<Payment> optionalPayment = purchase.getPayment();
		if(optionalPayment.isPresent()) {
			purchaseToHistoryDTO.setPaymentStatus(optionalPayment.get().getLastStatus());
		}
		return purchaseToHistoryDTO;
	}
}
