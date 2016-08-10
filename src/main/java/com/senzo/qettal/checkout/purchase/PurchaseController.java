package com.senzo.qettal.checkout.purchase;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senzo.qettal.checkout.security.LoggedUser;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

	@Autowired
	private PurchaseFactory purchaseFactory;
	@Autowired
	private LoggedUser loggedUser;

	@RequestMapping(method = POST)
	public ResponseEntity<String> create(@Valid @RequestBody PurchaseDTO purchaseDTO) {
		Optional<Purchase> optionalPurchase = purchaseFactory.create(purchaseDTO);
		if(!optionalPurchase.isPresent()){
			return new ResponseEntity<>(CONFLICT);
		}
		return new ResponseEntity<>(CREATED);
	}
}
