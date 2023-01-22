package com.projetopoo.service;

import com.projetopoo.config.Config;
import com.projetopoo.document.Engineer;
import com.projetopoo.document.WorkerAction;
import com.projetopoo.repository.WorkerActionRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Service
public class WorkerActionService {
    @Autowired
    private WorkerActionRepository repository;
    @Autowired
    private SequenceGeneratorService idService;
    private static final String SEQUENCE_NAME = WorkerAction.SEQUENCE_NAME;

    @Autowired
    private Config config;

    private EngineerService engineerService;

    public void setEngineerService(EngineerService engineerService){
        this.engineerService = engineerService;
    }

    public WorkerAction create(WorkerAction workerAction){
        workerAction.setId(idService.getSequenceNumber(SEQUENCE_NAME));
        return repository.save(workerAction);
    }

    public List<WorkerAction> showWorkerActions(){
        return repository.findAll();
    }

    public List<WorkerAction> showWorkerActionByDate(String data) { return repository.findByData(data); }

    public Object orderByDate(){
        Hashtable<String, Object> my_dict = new Hashtable<String, Object>();

        Aggregation agg = newAggregation(
                Aggregation.group("data"),
                Aggregation.sort(Sort.Direction.DESC, "_id")
        );

        MongoOperations mongoOps = config.mongoTemplate();

        for (Document date : mongoOps.aggregate(agg, "toolActions", Document.class).getMappedResults()){
            my_dict.put(date.get("_id").toString(), showWorkerActionByDate(date.get("_id").toString()));
        }

        return my_dict;
    }

    public WorkerAction showWorkerAction(long workerActionID){
        return repository.findById(workerActionID).get();
    }

    public WorkerAction update(WorkerAction workerAction){
        WorkerAction existingWorkerAction = showWorkerAction(workerAction.getId());

        existingWorkerAction.setDescription(workerAction.getDescription());
        existingWorkerAction.setData(workerAction.getData());
        existingWorkerAction.setReport(workerAction.isReport());

        return repository.save(existingWorkerAction);
    }

    public List<WorkerAction> showWorkerActionByWorkerID(long workerID){
        return repository.findByWorkerID(workerID);
    }

    public String deleteAllWorkerActionsByWorkerID(long workerID){
        for (WorkerAction workerAction: showWorkerActionByWorkerID(workerID)) {
            delete(workerAction.getId());
        }

        return "Deleted every WorkerAction that has a connection with the WorkerID " + workerID;
    }

    public List<WorkerAction> showWorkerActionByConstructionID(long constructionID){
        return repository.findByConstructionID(constructionID);
    }

    public String deleteAllWorkerActionsByConstructionID(long constructionID){
        for (WorkerAction workerAction: showWorkerActionByConstructionID(constructionID)) {
            delete(workerAction.getId());
        }

        return "Deleted every WorkerAction that has a connection with the ConstructionID " + constructionID;
    }

    public List<WorkerAction> showWorkerActionByEngineerID(long engineerID){
        return repository.findByEngineerID(engineerID);
    }

    public String deleteAllWorkerActionsByEngineerID(long engineerID){
        for (WorkerAction workerAction: showWorkerActionByEngineerID(engineerID)) {
            delete(workerAction.getId());
        }

        return "Deleted every WorkerAction that has a connection with the EngineerID " + engineerID;
    }

    public String delete(long workerActionID){
        repository.deleteById(workerActionID);

        return  "The Worker Action with the ID " + workerActionID + "was deleted.";
    }

    public Engineer getEngineer(long workerActionID){
        return engineerService.showEngineer(showWorkerAction(workerActionID).getEngineerID());
    }
}
