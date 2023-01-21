package com.projetopoo.repository;

import com.projetopoo.document.Construction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConstructionRepository extends MongoRepository<Construction, Long> {
    List<Construction> findByEngineerID(long engineerID);
}
