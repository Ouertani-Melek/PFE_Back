package com.backend.guestnhouse.repository.es;

import com.backend.guestnhouse.models.es.EsHouse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface EsHouseRepository extends ElasticsearchRepository<EsHouse, String> {

    List<EsHouse> findByHouseName(String houseName);

    List<EsHouse> findAllByHouseNameContainingAndAddressLineContainingAndHouseType(String houseName, String AddressLine,String houseType);

    List<EsHouse> findAllByHouseNameContainingOrAddressLineContainingOrHouseTypeContaining(String houseName, String AddressLine,String houseType);

}
