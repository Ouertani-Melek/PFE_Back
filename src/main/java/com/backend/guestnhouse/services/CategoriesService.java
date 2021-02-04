package com.backend.guestnhouse.services;


import com.backend.guestnhouse.models.Categories;
import com.backend.guestnhouse.models.Currency;
import com.backend.guestnhouse.models.House;
import com.backend.guestnhouse.repository.CategoriesRepsitory;
import com.backend.guestnhouse.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class CategoriesService {


    @Autowired
    HouseRepository houseRepository;

    @Autowired
    CategoriesRepsitory categoriesRepsitory;

    public List<Categories> getCategories() {
        return categoriesRepsitory.findAllCategories(0);
    }

    public String categoryToToHouse(String id,  String idCategory){
        House house = houseRepository.findById(id).orElse(null);
        Categories categories = categoriesRepsitory.findById(idCategory).orElse(null);
        if (house != null && categories != null) {
            if (house.getCategories() == null) {
                house.setCategories(new HashSet<>());
                house.getCategories().add(categories);
            } else house.getCategories().add(categories);
            houseRepository.save(house);
            return "Category Assigned to house";
        }else return "Category or house are null";
    }
}
