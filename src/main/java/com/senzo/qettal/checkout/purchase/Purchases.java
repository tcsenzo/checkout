package com.senzo.qettal.checkout.purchase;

import java.util.Optional;


public interface Purchases {

	Purchase save(Purchase purchase);

	Optional<Purchase> find(Long id);

	void update(Purchase purchase);

}
