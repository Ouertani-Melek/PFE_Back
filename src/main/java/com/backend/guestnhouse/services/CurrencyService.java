package com.backend.guestnhouse.services;


import com.backend.guestnhouse.models.Currency;
import com.backend.guestnhouse.models.House;
import com.backend.guestnhouse.repository.CurrencyRepository;
import com.backend.guestnhouse.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class CurrencyService {


    @Autowired
    HouseRepository houseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    public List<Currency> getCurrencies() {
        return currencyRepository.findAllCurrencies(0);
    }

    public String currencyToToHouse(String id,  String idCurrency){
        House house = houseRepository.findById(id).orElse(null);
        Currency currency = currencyRepository.findById(idCurrency).orElse(null);
        if (house != null && currency != null) {
            if (house.getCurrencies() == null) {
                house.setCurrencies(new HashSet<>());
                house.getCurrencies().add(currency);
            } else house.getCurrencies().add(currency);
            houseRepository.save(house);
            return "Currency Assigned to house";
        }else return "Currency or house are null";
    }
}
