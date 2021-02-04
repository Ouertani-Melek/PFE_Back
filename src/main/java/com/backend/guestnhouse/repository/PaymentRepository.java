package com.backend.guestnhouse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.guestnhouse.models.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {

}
