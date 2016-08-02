package com.senzo.qettal.checkout.payment;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moip.API;
import br.com.moip.request.CreditCardRequest;
import br.com.moip.request.FundingInstrumentRequest;
import br.com.moip.request.HolderRequest;
import br.com.moip.request.PaymentRequest;
import br.com.moip.request.PhoneRequest;
import br.com.moip.request.TaxDocumentRequest;
import br.com.moip.resource.Payment;

import com.senzo.qettal.checkout.purchase.Purchase;
import com.senzo.qettal.checkout.purchase.Purchases;
import com.senzo.qettal.checkout.security.LoggedUser;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

	@Autowired
	private LoggedUser loggedUser;
	@Autowired
	private Purchases purchases;
	@Autowired
	private API api;
	
	@RequestMapping(method = POST)
	public ResponseEntity<Payment> create(@Valid @RequestBody PaymentDTO paymentDTO) {
		Optional<Purchase> optionalPurchase = purchases.find(paymentDTO.getPurchaseId());
		if(!optionalPurchase.isPresent())
			return new ResponseEntity<>(NOT_FOUND);

		Purchase purchase = optionalPurchase.get();
		if(!purchase.isOwnedBy(loggedUser.getUser().get()))
			return new ResponseEntity<>(FORBIDDEN);
		
		HolderRequest holderRequest = new HolderRequest()
		        .fullname(paymentDTO.getFullName())
		        .birthdate(paymentDTO.getBirthDate())
		        .phone(new PhoneRequest()
		                        .setAreaCode(paymentDTO.getPhoneAreaCode())
		                        .setNumber(paymentDTO.getPhone()))
		        .taxDocument(TaxDocumentRequest.cpf(paymentDTO.getCpf()));
		
		CreditCardRequest creditCardRequest = new CreditCardRequest()
		        .hash(paymentDTO.getCreditCardHash())
		        .holder(holderRequest);
		
		PaymentRequest paymentRequest = new PaymentRequest()
		        .orderId(purchase.getReferenceId())
		        .installmentCount(1)
		        .fundingInstrument(new FundingInstrumentRequest().creditCard(creditCardRequest));
		
		return new ResponseEntity<>(api.payment().create(paymentRequest), OK);
	}
}
