package com.projetopoo.repository;

import com.projetopoo.document.Tool;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ToolRepository extends MongoRepository<Tool, Long> {
    List<Tool> findByEngineerID(long engineerID);
}
