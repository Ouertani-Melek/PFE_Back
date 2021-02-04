package com.backend.guestnhouse.controllers;


import com.backend.guestnhouse.models.Categories;
import com.backend.guestnhouse.repository.CategoriesRepsitory;
import com.backend.guestnhouse.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    CategoriesRepsitory categoriesRepsitory;

    @RequestMapping(value = "/allCategories" , method = RequestMethod.GET)
    public ResponseEntity<?> getCategories(){
        return ResponseEntity.ok(categoriesService.getCategories());
    }

    @RequestMapping(value = "/createCategory",method = RequestMethod.POST)
    public void createCategory(@Valid @RequestBody Categories categories)
    {   categories.setCreated(new Date());
        categoriesRepsitory.save(categories); }

    @RequestMapping(value = "/updateCategory/{id}", method = RequestMethod.PUT)
    public void updateCategory(@PathVariable("id") String id, @Valid @RequestBody Categories categories) {
        categories.setId(id);
        categories.setModified(new Date());
        categoriesRepsitory.save(categories);
    }

    @RequestMapping(value = "/archiveCategory/{id}", method = RequestMethod.DELETE)
    public String archiveCategory(@PathVariable("id") String id ){
        Categories categories =  categoriesRepsitory.findById(id).orElse(null);
        categories.setId(id);
        categories.setArchived(1);
        categoriesRepsitory.save(categories);
        return "Category archived";
    }

    @RequestMapping(value = "/CategoryToHouse/{id}/{idCategory}",method = RequestMethod.PUT)
    public String AssignCategoryToHouse(@PathVariable("id") String id, @PathVariable("idCategory") String idCategory)
    {
        return categoriesService.categoryToToHouse(id, idCategory);
    }
}
