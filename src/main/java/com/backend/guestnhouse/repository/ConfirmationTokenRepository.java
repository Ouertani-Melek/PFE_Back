package com.backend.guestnhouse.repository;

import org.springframework.data.repository.CrudRepository;

import com.backend.guestnhouse.models.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
	
    ConfirmationToken findByConfirmationToken(String confirmationToken);

}
