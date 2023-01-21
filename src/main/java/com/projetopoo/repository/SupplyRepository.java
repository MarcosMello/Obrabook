package com.projetopoo.repository;

import com.projetopoo.document.Supply;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SupplyRepository extends MongoRepository<Supply, Long> {
    List<Supply> findByEngineerID(long engineerID);
}
