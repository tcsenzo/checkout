package com.senzo.qettal.checkout.payment;

import java.util.Optional;

public interface Payments {

	void save(Payment payment);

	Optional<Payment> findByPurchaseUniqueId(String orderUniqueId);

	void update(Payment payment);

}
