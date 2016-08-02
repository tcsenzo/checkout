package com.senzo.qettal.checkout.purchase;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseDAO implements Purchases{

	@Autowired
	private EntityManager em;
	
	@Override
	@Transactional
	public Purchase save(Purchase purchase) {
		em.persist(purchase);
		return purchase;
	}
	
	@Override
	@Transactional
	public void update(Purchase purchase) {
		em.merge(purchase);
	}

	@Override
	public Purchase find(Long id) {
		return em.find(Purchase.class, id);
	}

}
