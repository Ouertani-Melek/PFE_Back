package com.backend.guestnhouse.repository;


import com.backend.guestnhouse.models.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository  extends MongoRepository<Currency, String> {
    @Query("{archived : ?0}")
    List<Currency> findAllCurrencies(int archived);
}
