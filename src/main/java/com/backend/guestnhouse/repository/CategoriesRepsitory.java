package com.backend.guestnhouse.repository;

import com.backend.guestnhouse.models.Categories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoriesRepsitory extends MongoRepository<Categories, String> {
    @Query("{archived : ?0}")
    List<Categories> findAllCategories(int archived);
}
