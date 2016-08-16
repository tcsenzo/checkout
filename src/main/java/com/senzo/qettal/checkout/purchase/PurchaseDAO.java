package com.senzo.qettal.checkout.purchase;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseDAO implements Purchases {

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
	public Optional<Purchase> find(Long id) {
		return Optional.ofNullable(em.find(Purchase.class, id));
	}

	@Override
	public Optional<Purchase> findByUniqueId(String purchaseUniqueId) {
		String hql = "from " + Purchase.class.getSimpleName() + " p where p.uniqueId = :uniqueId";
		try{
			Purchase purchase = em.createQuery(hql, Purchase.class)
					.setParameter("uniqueId", purchaseUniqueId)
					.getSingleResult();
			return Optional.of(purchase);
		} catch(NoResultException e) {
			return Optional.empty();
		}
	}

}
