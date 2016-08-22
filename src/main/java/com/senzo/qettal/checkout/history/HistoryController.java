package com.senzo.qettal.checkout.history;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senzo.qettal.checkout.purchase.Purchases;
import com.senzo.qettal.checkout.security.LoggedUser;


@RestController
@RequestMapping("/history")
public class HistoryController {

	@Autowired
	private LoggedUser loggedUser;
	@Autowired
	private Purchases purchases;
	@Autowired
	private PurchaseToHistoryConverter converter;
	
	@RequestMapping(method=GET)
	public ResponseEntity<List<PurchaseToHistoryDTO>> list(){
		List<PurchaseToHistoryDTO> purchaseList = purchases.of(loggedUser.getUser().get())
				.stream()
				.map(converter::convert)
				.collect(toList());
		
		return new ResponseEntity<>(purchaseList, OK);
	}
	
}
