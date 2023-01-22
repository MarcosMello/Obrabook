package com.projetopoo.service;

import com.projetopoo.config.Config;
import com.projetopoo.document.Engineer;
import com.projetopoo.document.ToolAction;
import com.projetopoo.repository.ToolActionRepository;
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
public class ToolActionService {
    @Autowired
    private ToolActionRepository repository;
    @Autowired
    private SequenceGeneratorService idService;
    private static final String SEQUENCE_NAME = ToolAction.SEQUENCE_NAME;

    private EngineerService engineerService;

    @Autowired
    private Config config;

    public void setEngineerService(EngineerService engineerService){
        this.engineerService = engineerService;
    }

    public ToolAction create(ToolAction toolAction){
        toolAction.setId(idService.getSequenceNumber(SEQUENCE_NAME));
        return repository.save(toolAction);
    }

    public List<ToolAction> showToolActions(){
        return repository.findAll();
    }

    public ToolAction showToolAction(long toolActionID){
        return repository.findById(toolActionID).get();
    }

    public List<ToolAction> showToolActionByDate(String data) { return repository.findByData(data); }

    public Object orderByDate(){
        Hashtable<String, Object> my_dict = new Hashtable<String, Object>();

        Aggregation agg = newAggregation(
                Aggregation.group("data"),
                Aggregation.sort(Sort.Direction.DESC, "_id")
        );

        MongoOperations mongoOps = config.mongoTemplate();

        for (Document date : mongoOps.aggregate(agg, "toolActions", Document.class).getMappedResults()){
            my_dict.put(date.get("_id").toString(), showToolActionByDate(date.get("_id").toString()));
        }

        return my_dict;
    }

    public ToolAction update(ToolAction toolAction){
        ToolAction existingToolAction = showToolAction(toolAction.getId());

        existingToolAction.setData(toolAction.getData());
        existingToolAction.setDescription(toolAction.getDescription());
        existingToolAction.setReport(toolAction.isReport());

        return repository.save(existingToolAction);
    }

    public List<ToolAction> showToolActionByToolID(long toolID){
        return repository.findByToolID(toolID);
    }

    public String deleteAllToolActionsByToolID(long toolID){
        for (ToolAction toolAction: showToolActionByToolID(toolID)) {
            delete(toolAction.getId());
        }

        return "Deleted every ToolAction that has a connection with the ToolID " + toolID;
    }

    public List<ToolAction> showToolActionByConstructionID(long constructionID){
        return repository.findByConstructionID(constructionID);
    }

    public String deleteAllToolActionsByConstructionID(long constructionID){
        for (ToolAction toolAction: showToolActionByConstructionID(constructionID)) {
            delete(toolAction.getId());
        }

        return "Deleted every ToolAction that has a connection with the ConstructionID " + constructionID;
    }

    public List<ToolAction> showToolActionByEngineerID(long engineerID){
        return repository.findByEngineerID(engineerID);
    }

    public String deleteAllToolActionsByEngineerID(long engineerID){
        for (ToolAction toolAction: showToolActionByEngineerID(engineerID)) {
            delete(toolAction.getId());
        }

        return "Deleted every ToolAction that has a connection with the EngineerID " + engineerID;
    }

    public String delete(long toolActionID){
        repository.deleteById(toolActionID);

        return  "The Tool Action with the ID " + toolActionID + "was deleted.";
    }

    public Engineer getEngineer(long toolActionID){
        return engineerService.showEngineer(showToolAction(toolActionID).getEngineerID());
    }
}
