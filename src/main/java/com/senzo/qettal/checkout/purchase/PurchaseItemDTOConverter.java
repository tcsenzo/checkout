package com.senzo.qettal.checkout.purchase;

import static java.util.stream.Collectors.groupingBy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.senzo.qettal.checkout.ticket.TicketType;

@Component
public class PurchaseItemDTOConverter {

	public List<PurchaseItemDTO> convert(Purchase purchase) {
		Map<TicketType, List<PurchaseItem>> itemsByType = purchase.getItems().stream().collect(groupingBy(PurchaseItem::getType));
		
		return itemsByType.entrySet()
				.stream()
				.filter(e -> !e.getValue().isEmpty())
				.map(e -> {
					List<PurchaseItem> items = e.getValue();
					BigDecimal unitPrice = items.stream().findAny().get().getPrice();
					return new PurchaseItemDTO(Long.valueOf(items.size()), e.getKey(), unitPrice);	
				})
				.collect(Collectors.toList());		
	}

}
