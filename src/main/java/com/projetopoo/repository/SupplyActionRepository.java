package com.projetopoo.repository;

import com.projetopoo.document.SupplyAction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SupplyActionRepository extends MongoRepository<SupplyAction, Long> {
    List<SupplyAction> findByEngineerID(long engineerID);

    List<SupplyAction> findByConstructionID(long constructionID);

    List<SupplyAction> findBySupplyID(long supplyID);
}
