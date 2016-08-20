package com.senzo.qettal.checkout.users;

import java.util.Optional;


public interface Users {
	User save(User user);
	Optional<User> findByAuthId(String email, String authId);
}
