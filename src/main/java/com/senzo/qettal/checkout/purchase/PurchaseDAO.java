package com.senzo.qettal.checkout.purchase;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senzo.qettal.checkout.users.User;

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
		try{
			String hql = "from " + Purchase.class.getSimpleName() + " p where p.uniqueId = :uniqueId";
			Purchase purchase = em.createQuery(hql, Purchase.class)
					.setParameter("uniqueId", purchaseUniqueId)
					.getSingleResult();
			return Optional.of(purchase);
		} catch(NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Purchase> of(User user) {
		String hql = "from " + Purchase.class.getSimpleName() + " p where p.owner = :owner";
		return em.createQuery(hql, Purchase.class)
				.setParameter("owner", user)
				.getResultList();
	}

}
