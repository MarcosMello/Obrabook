package com.projetopoo.repository;

import com.projetopoo.document.ToolAction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ToolActionRepository extends MongoRepository<ToolAction, Long> {
    List<ToolAction> findByEngineerID(long engineerID);

    List<ToolAction> findByConstructionID(long constructionID);

    List<ToolAction> findByToolID(long toolID);

    List<ToolAction> findByData(String data);
}
