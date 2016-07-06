package com.senzo.qettal.checkout.purchase;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;
import br.com.moip.request.CustomerRequest;
import br.com.moip.request.OrderRequest;

import com.senzo.qettal.checkout.security.LoggedUser;
import com.senzo.qettal.checkout.users.User;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

	@Autowired
	private PurchaseConverter purchaseConverter;
	@Autowired
	private Purchases purchases;
	@Autowired
	private LoggedUser loggedUser;

	@RequestMapping(method = POST)
	public ResponseEntity<String> create(@Valid @RequestBody PurchaseDTO purchaseDTO) {
		Purchase purchase = purchaseConverter.convert(purchaseDTO);
		purchases.save(purchase);
//		
//		Authentication auth = new BasicAuth("VXOMXSU28LXTVDG1V1L8ZLFAJX10PQYT", "ABEFG291RV8LR49QQAEAYFRYHFAY61CEFFZZGHBD");
//		Client client = new Client(Client.SANDBOX, auth);
//		API api = new API(client);
//		OrderRequest orderRequest = new OrderRequest()
//	        .ownId(purchase.getId().toString());
//	
//		List<PurchaseItem> items = purchase.getItems();
//		for (PurchaseItem purchaseItem : items) {
//			orderRequest.addItem(purchaseItem.getName(), purchaseItem.getQuantity().intValue(), purchaseItem.getDescription(), purchaseItem.getUnitPrice().intValue());
//		}
//		
//		User user = loggedUser.getUser().get();
//		
//	    orderRequest.customer(
//	    		new CustomerRequest()
//	                    .ownId(user.getId().toString())
//	                    .fullname(user.getName())
//	                    .email(user.getEmail()));
//		
//		api.order().create(orderRequest);
		
		return new ResponseEntity<>(OK);
	}
}
