package com.backend.guestnhouse.services.es;


import com.backend.guestnhouse.models.House;
import com.backend.guestnhouse.models.es.EsHouse;
import com.backend.guestnhouse.repository.es.EsHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EsHouseService {

    @Autowired
    EsHouseRepository esHouseRepository;


    public void houseToEs(House house) {
        EsHouse esHouse = new EsHouse();
        if (house != null) {
            esHouse.setId(house.getId());
            esHouse.setHouseName(house.getHouseName());
            esHouse.setDescription(house.getDescription());
            esHouse.setAddressLine(house.getAddressLine());
            esHouse.setHouseType(house.getHouseType());
            esHouse.setImages(house.getImages());
            esHouse.setLatitude(house.getLatitude());
            esHouse.setLongitude(house.getLongitude());
            esHouse.setPriceHouse((house.getPriceHouse()));
            esHouse.setArchived(house.getArchived());
            System.out.println(esHouse.getId() + " " + esHouse.getHouseName());
            esHouseRepository.save(esHouse);
        }
    }

    public List<EsHouse> queryHouse(String content) {
       // return esHouseRepository.findAllByHouseNameContainingOrDescriptionContainingOrHouseTypeContaining(content, content, content);
        List<EsHouse> esHouses = new ArrayList<>();
        if (content.contains(" ")) {
            String[] contents = content.split(" ");

            for (int i = 0; i < contents.length; i++) {
                esHouses.addAll(esHouseRepository.findAllByHouseNameContainingAndAddressLineContainingAndHouseType(contents[i], contents[i], contents[i]));
              //  System.out.println(esHouses);
            }
            esHouses = esHouses.stream()
                    .distinct().filter(e -> e.getArchived() == 0 )
                    .collect(Collectors.toList());
            return esHouses;
        }else esHouses = esHouseRepository.findAllByHouseNameContainingAndAddressLineContainingAndHouseType(content, content, content);
              esHouses = esHouses.stream()
                .distinct().filter(e -> e.getArchived() == 0 )
                .collect(Collectors.toList());
              return esHouses;
    }
}
