package com.backend.guestnhouse.controllers;


import com.backend.guestnhouse.models.Currency;
import com.backend.guestnhouse.repository.CurrencyRepository;
import com.backend.guestnhouse.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CurrencyService currencyService;


    @RequestMapping(value = "/allCurrencies" , method = RequestMethod.GET)
    public ResponseEntity<?> getCurrencies(){
        return ResponseEntity.ok(currencyService.getCurrencies());
    }

    @RequestMapping(value = "/createCurrency",method = RequestMethod.POST)
    public void createCurrency(@Valid @RequestBody Currency currency)
    {   currency.setCreated(new Date());
        currencyRepository.save(currency); }

    @RequestMapping(value = "/updateCurrency/{id}", method = RequestMethod.PUT)
    public void updateCurrency(@PathVariable("id") String id, @Valid @RequestBody Currency currency) {
        currency.setId(id);
        currency.setModified(new Date());
        currencyRepository.save(currency);
    }

    @RequestMapping(value = "/archiveCurrency/{id}", method = RequestMethod.DELETE)
    public String archiveRoom(@PathVariable("id") String id ){
        Currency currency =  currencyRepository.findById(id).orElse(null);
        currency.setId(id);
        currency.setArchived(1);
        currencyRepository.save(currency);
        return "Currency archived";
    }

    @RequestMapping(value = "/CurrencyToHouse/{id}/{idCurrency}",method = RequestMethod.PUT)
    public String AssignCurrencyToHouse(@PathVariable("id") String id, @PathVariable("idCurrency") String idCurrency)
    {
        return currencyService.currencyToToHouse(id, idCurrency);
    }
}
