package com.projetopoo.repository;

import com.projetopoo.document.Step;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StepRepository extends MongoRepository<Step, Long> {
    List<Step> findByConstructionID(long constructionID);
}
