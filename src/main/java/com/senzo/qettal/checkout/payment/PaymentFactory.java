package com.senzo.qettal.checkout.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senzo.qettal.checkout.moip.MoipApiWrapper;
import com.senzo.qettal.checkout.purchase.Purchase;

@Component
public class PaymentFactory {

	@Autowired
	private MoipApiWrapper moip;

	public Payment create(PaymentDTO paymentDTO, Purchase purchase) {
		moip.pay(paymentDTO, purchase.getReferenceId());		
		return new Payment(purchase);
	}
	
	
}
