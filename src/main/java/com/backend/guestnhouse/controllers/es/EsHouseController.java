package com.backend.guestnhouse.controllers.es;

import com.backend.guestnhouse.models.es.EsHouse;
import com.backend.guestnhouse.repository.es.EsHouseRepository;
import com.backend.guestnhouse.services.es.EsHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/es")
public class EsHouseController {

    @Autowired
    EsHouseService esHouseService;

    @Autowired
    EsHouseRepository esHouseRepository;

    @GetMapping("/all")
    public Iterable<EsHouse> getAll(){
        return esHouseRepository.findAll();
    }

    @GetMapping("/search/{content}")
    public List<EsHouse> searchHouse(@PathVariable String content){
        try {
            return esHouseService.queryHouse(content);
        }
        catch (InvalidDataAccessApiUsageException e){
            return null;
        }

    }
}
