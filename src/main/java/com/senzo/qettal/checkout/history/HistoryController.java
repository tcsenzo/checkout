package com.senzo.qettal.checkout.history;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senzo.qettal.checkout.purchase.Purchases;
import com.senzo.qettal.checkout.security.LoggedUser;


@RestController
public class HistoryController {

	@Autowired
	private LoggedUser loggedUser;
	@Autowired
	private Purchases purchases;
	@Autowired
	private PurchaseToHistoryConverter converter;
	
	@RequestMapping(path="/history", method=GET)
	public ResponseEntity<List<PurchaseToHistoryDTO>> buyerHistory(){
		List<PurchaseToHistoryDTO> purchaseList = purchases.boughtBy(loggedUser.getUser().get())
				.stream()
				.map(converter::convert)
				.collect(toList());
		
		return new ResponseEntity<>(purchaseList, OK);
	}
	
	@RequestMapping(path="/theaters/{theaterId}/history", method=GET)
	public ResponseEntity<List<TheaterPurchaseToHistoryDTO>> sellerHistory(@PathVariable("theaterId") Long theaterId){
		List<TheaterPurchaseToHistoryDTO> purchaseList = purchases.soldBy(theaterId)
				.stream()
				.map(converter::convertForTheater)
				.collect(toList());
		
		return new ResponseEntity<>(purchaseList, OK);
	}
	
}
