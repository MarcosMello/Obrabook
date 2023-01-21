package com.projetopoo.service;

import com.projetopoo.document.Construction;
import com.projetopoo.document.Engineer;
import com.projetopoo.document.Supply;
import com.projetopoo.document.SupplyAction;
import com.projetopoo.repository.SupplyActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyActionService {
    @Autowired
    private SupplyActionRepository repository;
    @Autowired
    private SequenceGeneratorService idService;
    private static final String SEQUENCE_NAME = SupplyAction.SEQUENCE_NAME;

    private EngineerService engineerService;

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private ConstructionService constructionService;

    public void setEngineerService(EngineerService engineerService){
        this.engineerService = engineerService;
    }

    public SupplyAction create(SupplyAction supplyAction){
        supplyAction.setId(idService.getSequenceNumber(SEQUENCE_NAME));
        return repository.save(supplyAction);
    }

    public List<SupplyAction> showSupplyActions(){
        return repository.findAll();
    }

    public SupplyAction showSupplyAction(long supplyActionID){
        return repository.findById(supplyActionID).get();
    }

    public SupplyAction update(SupplyAction supplyAction){
        SupplyAction existingSupplyAction = showSupplyAction(supplyAction.getId());

        existingSupplyAction.setDescription(supplyAction.getDescription());
        existingSupplyAction.setData(supplyAction.getData());
        existingSupplyAction.setReport(supplyAction.isReport());

        return repository.save(existingSupplyAction);
    }

    public List<SupplyAction> showSupplyActionBySupplyID(long supplyID){
        return repository.findBySupplyID(supplyID);
    }

    public String deleteAllSupplyActionsBySupplyID(long supplyID){
        for (SupplyAction supplyAction: showSupplyActionBySupplyID(supplyID)) {
            delete(supplyAction.getId());
        }

        return "Deleted every SupplyAction that has a connection with the SupplyID " + supplyID;
    }

    public List<SupplyAction> showSupplyActionByConstructionID(long constructionID){
        return repository.findByConstructionID(constructionID);
    }

    public String deleteAllSupplyActionsByConstructionID(long constructionID){
        for (SupplyAction supplyAction: showSupplyActionByConstructionID(constructionID)) {
            delete(supplyAction.getId());
        }

        return "Deleted every SupplyAction that has a connection with the ConstructionID " + constructionID;
    }

    public List<SupplyAction> showSupplyActionByEngineerID(long engineerID){
        return repository.findByEngineerID(engineerID);
    }

    public String deleteAllSupplyActionsByEngineerID(long engineerID){
        for (SupplyAction supplyAction: showSupplyActionByEngineerID(engineerID)) {
            delete(supplyAction.getId());
        }

        return "Deleted every SupplyAction that has a connection with the EngineerID " + engineerID;
    }

    public String delete(long supplyActionID){
        repository.deleteById(supplyActionID);

        return  "The Supply Action with the ID " + supplyActionID + "was deleted.";
    }

    public Engineer getEngineer(long supplyActionID){
        return engineerService.showEngineer(showSupplyAction(supplyActionID).getEngineerID());
    }

    public Supply getSupply(long supplyActionID){
        return supplyService.showSupply(showSupplyAction(supplyActionID).getSupplyID());
    }

    public Construction getConstruction(long supplyActionID){
        return constructionService.showConstruction(showSupplyAction(supplyActionID).getConstructionID());
    }
}
