package com.senzo.qettal.checkout.payment;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentDAO implements Payments{

	@Autowired
	private EntityManager em;
	
	@Override
	@Transactional
	public void save(Payment payment) {
		em.persist(payment);
	}

	@Override
	@Transactional
	public void update(Payment payment) {
		em.merge(payment);
	}
	
	@Override
	public Optional<Payment> findByPurchaseUniqueId(String uniqueId) {
		try {
			String hql = "from Payment p where p.purchase.uniqueId = :uniqueId";
			return Optional.of(em.createQuery(hql, Payment.class)
					.setParameter("uniqueId", uniqueId)
					.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}
	
}
