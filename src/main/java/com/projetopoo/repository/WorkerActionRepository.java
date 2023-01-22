package com.projetopoo.repository;

import com.projetopoo.document.WorkerAction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkerActionRepository extends MongoRepository<WorkerAction, Long> {
    List<WorkerAction> findByEngineerID(long engineerID);

    List<WorkerAction> findByWorkerID(long workerID);

    List<WorkerAction> findByConstructionID(long constructionID);

    List<WorkerAction> findByData(String data);
}
