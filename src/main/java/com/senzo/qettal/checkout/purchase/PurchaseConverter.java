package com.senzo.qettal.checkout.purchase;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senzo.qettal.checkout.security.LoggedUser;

@Component
public class PurchaseConverter {
	
	@Autowired
	private PurchaseItemConverter itemConverter;
	@Autowired
	private LoggedUser loggedUser;
	
	public Purchase convert(PurchaseDTO purchaseDTO) {
		List<PurchaseItem> items = purchaseDTO.getItems().stream().map(itemConverter::convert).collect(toList());
		return new Purchase(loggedUser.getUser().get(), items);
	}

}
