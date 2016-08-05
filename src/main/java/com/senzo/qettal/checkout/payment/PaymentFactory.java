package com.senzo.qettal.checkout.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senzo.qettal.checkout.moip.MoipApiWrapper;
import com.senzo.qettal.checkout.purchase.Purchase;

@Component
public class PaymentFactory {

	@Autowired
	private MoipApiWrapper moip;
	@Autowired
	private Payments payments;

	public Payment create(PaymentDTO paymentDTO, Purchase purchase) {
		Payment payment = new Payment(purchase);
		payments.save(payment);
		moip.pay(paymentDTO, purchase.getReferenceId());		
		return payment;
	}
	
	
}
