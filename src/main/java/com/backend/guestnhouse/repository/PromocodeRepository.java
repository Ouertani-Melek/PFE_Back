package com.backend.guestnhouse.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.guestnhouse.models.Promocode;

public interface PromocodeRepository extends MongoRepository<Promocode, String> {

	Optional<Promocode> findByCode(String code);
}
