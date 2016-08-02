package com.senzo.qettal.checkout.payment;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;
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
	

	@RequestMapping(method = POST)
	public Payment create(@Valid @RequestBody PaymentDTO paymentDTO) {
		Purchase purchase = purchases.find(paymentDTO.getPurchaseId());
		
		Authentication auth = new BasicAuth("VXOMXSU28LXTVDG1V1L8ZLFAJX10PQYT", "ABEFG291RV8LR49QQAEAYFRYHFAY61CEFFZZGHBD");
		Client client = new Client(Client.SANDBOX, auth);
		API api = new API(client);

		HolderRequest holderRequest = new HolderRequest()
		        .fullname(paymentDTO.getFullName())
		        .birthdate(paymentDTO.getBirthDate())
		        .phone(
		                new PhoneRequest()
		                        .setAreaCode(paymentDTO.getPhoneAreaCode())
		                        .setNumber(paymentDTO.getPhone())
		        )
		        .taxDocument(TaxDocumentRequest.cpf(paymentDTO.getCPF()));
		
		CreditCardRequest creditCardRequest = new CreditCardRequest()
		        .hash(paymentDTO.getCreditCardHash())
		        .holder(
		                holderRequest
		        );
		
		PaymentRequest paymentRequest = new PaymentRequest()
		        .orderId(purchase.getReferenceId())
		        .installmentCount(1)
		        .fundingInstrument(
		                new FundingInstrumentRequest()
		                        .creditCard(creditCardRequest)
		        );
		
		return api.payment().create(paymentRequest);
	}
}
