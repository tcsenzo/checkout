package com.senzo.qettal.checkout.purchase;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.moip.resource.Order;

import com.senzo.qettal.checkout.moip.MoipApiWrapper;
import com.senzo.qettal.checkout.security.LoggedUser;

@Component
public class PurchaseFactory {
	
	@Autowired
	private PurchaseItemConverter itemConverter;
	@Autowired
	private LoggedUser loggedUser;
	@Autowired
	private MoipApiWrapper moip;
	@Autowired
	private Purchases purchases;
	
	public Optional<Purchase> create(PurchaseDTO purchaseDTO) {
		try{
			Purchase purchase = new Purchase(loggedUser.getUser().get());
			List<PurchaseItem> items = purchaseDTO.getItems().stream().map(dto -> itemConverter.convert(dto, purchase)).collect(toList());
			purchase.addItems(items);
			purchases.save(purchase);
			Order order = moip.order(purchase);
			purchase.addMoipInfo(order, purchases);
			return Optional.of(purchase);
		} catch (EventNotAvailableException e){
			return Optional.empty();
		}
	}

}
