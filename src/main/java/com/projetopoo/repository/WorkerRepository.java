package com.projetopoo.repository;

import com.projetopoo.document.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkerRepository extends MongoRepository<Worker, Long> {
    List<Worker> findByEngineerID(long engineerID);
}
