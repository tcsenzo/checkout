package com.senzo.qettal.checkout.purchase;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpMethod.PUT;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.moip.resource.Order;

import com.senzo.qettal.checkout.moip.MoipApiWrapper;
import com.senzo.qettal.checkout.security.LoggedUser;

@Component
public class PurchaseFactory {
	
	
	@Value("${url.checkoutEvents}")
	private String checkoutEventsUrl;
	
	@Autowired
	private PurchaseItemConverter itemConverter;
	@Autowired
	private LoggedUser loggedUser;
	@Autowired
	private MoipApiWrapper moip;
	@Autowired
	private Purchases purchases;

	@Transactional
	public Optional<Purchase> create(PurchaseDTO purchaseDTO) {
		try{
			Long totalQuantity = purchaseDTO.getTotalQuantity();
			CheckoutToEventDTO checkoutDto = new CheckoutToEventDTO(totalQuantity);
			ResponseEntity<PurchaseEventDTO> response = new RestTemplate().exchange(checkoutEventsUrl + "/" + purchaseDTO.getEventId(), PUT, new HttpEntity<CheckoutToEventDTO>(checkoutDto), PurchaseEventDTO.class);
			PurchaseEventDTO event = response.getBody();
			
			Purchase purchase = new Purchase(loggedUser.getUser().get(), event.getName(), event.getDescription(), event.getScheduledDate(), event.getTheaterName(), event.getTheaterAddress());
			List<PurchaseItem> items = purchaseDTO.getItems()
					.stream()
					.flatMap(dto -> itemConverter.convert(dto, event, purchase))
					.collect(toList());
			purchase.addItems(items);
			purchases.save(purchase);
			
			Order order = moip.order(purchase);
			purchase.addMoipInfo(order, purchases);
			return Optional.of(purchase);
		} catch (HttpClientErrorException e)   {
			return Optional.empty();
		}
	}

}
