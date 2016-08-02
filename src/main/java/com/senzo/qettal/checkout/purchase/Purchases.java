package com.senzo.qettal.checkout.purchase;

public interface Purchases {

	Purchase save(Purchase purchase);

	Purchase find(Long id);

	void update(Purchase purchase);

}
