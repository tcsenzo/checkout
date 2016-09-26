package com.senzo.qettal.checkout.moip;

import java.text.DecimalFormat;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.moip.API;
import br.com.moip.exception.ValidationException;
import br.com.moip.request.CreditCardRequest;
import br.com.moip.request.CustomerRequest;
import br.com.moip.request.FundingInstrumentRequest;
import br.com.moip.request.HolderRequest;
import br.com.moip.request.OrderRequest;
import br.com.moip.request.PaymentRequest;
import br.com.moip.request.PhoneRequest;
import br.com.moip.request.TaxDocumentRequest;
import br.com.moip.resource.Order;
import br.com.moip.resource.Payment;

import com.senzo.qettal.checkout.payment.PaymentDTO;
import com.senzo.qettal.checkout.purchase.Purchase;
import com.senzo.qettal.checkout.purchase.PurchaseItem;
import com.senzo.qettal.checkout.security.LoggedUser;
import com.senzo.qettal.checkout.users.User;

@Component
public class MoipApiWrapper {

	@Autowired
	private LoggedUser loggedUser;

	@Autowired
	private API api;

	public Order order(Purchase purchase) {
		try {
			OrderRequest orderRequest = new OrderRequest().ownId(purchase.getUniqueId().toString());

			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			df.setMinimumFractionDigits(2);

			Set<PurchaseItem> items = purchase.getItems();
			for (PurchaseItem purchaseItem : items) {
				orderRequest.addItem(purchase.getEventName(), 1, purchase.getEventDescription(),
						Integer.valueOf(df.format(purchaseItem.getPrice()).replace(".", "")));
			}

			User user = loggedUser.getUser().get();

			orderRequest.customer(new CustomerRequest().ownId(user.getUniqueId().toString()).fullname(user.getName())
					.email(user.getEmail()));

			return api.order().create(orderRequest);
		} catch (ValidationException e) {
			throw new RuntimeException("Moip threw an exception: " + e.getErrors(), e);
		}
	}

	public Payment pay(PaymentDTO paymentDTO, String referenceId) {
		try {
			HolderRequest holderRequest = new HolderRequest()
					.fullname(paymentDTO.getFullName())
					.birthdate(paymentDTO.getBirthDate())
					.phone(new PhoneRequest().setAreaCode(paymentDTO.getPhoneAreaCode()).setNumber(
							paymentDTO.getPhone())).taxDocument(TaxDocumentRequest.cpf(paymentDTO.getCpf()));

			CreditCardRequest creditCardRequest = new CreditCardRequest().hash(paymentDTO.getCreditCardHash()).holder(
					holderRequest);

			PaymentRequest paymentRequest = new PaymentRequest().orderId(referenceId).installmentCount(1)
					.fundingInstrument(new FundingInstrumentRequest().creditCard(creditCardRequest));

			Payment payment = api.payment().create(paymentRequest);
			return payment;
		} catch (ValidationException e) {
			throw new RuntimeException("Moip threw an exception: " + e.getErrors(), e);
		}
	}

}
