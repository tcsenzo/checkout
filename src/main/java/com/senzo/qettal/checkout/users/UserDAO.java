package com.senzo.qettal.checkout.users;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAO implements Users {

	@Autowired
	private EntityManager em;
	
	@Override
	@Transactional
	public User save(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public Optional<User> findByAuthId(String email, String authId) {
		try{
			return Optional.of(em.createQuery("from User where email = :email and authId = :authId", User.class)
					.setParameter("email", email)
					.setParameter("authId", authId)
					.getSingleResult());
		} catch (NonUniqueResultException | NoResultException e){
			return Optional.empty();
		}
	}

}
