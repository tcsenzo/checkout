package com.senzo.qettal.checkout.purchase;

import java.util.List;
import java.util.Optional;

import com.senzo.qettal.checkout.users.User;


public interface Purchases {

	Purchase save(Purchase purchase);

	Optional<Purchase> find(Long id);

	void update(Purchase purchase);

	Optional<Purchase> findByUniqueId(String purchaseUniqueId);

	List<Purchase> boughtBy(User user);
	
	List<Purchase> soldBy(Long theaterId);


}
