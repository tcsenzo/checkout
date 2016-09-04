package com.senzo.qettal.checkout.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseConverter {
	
	@Autowired
	private PurchaseItemDTOConverter itemConverter;
	
	public PurchaseDTO convert(Purchase purchase){
		List<PurchaseItemDTO> items = itemConverter.convert(purchase);
		return new PurchaseDTO(items, purchase.getId());
	}
}
