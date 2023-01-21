package com.projetopoo.service;

import com.projetopoo.document.Client;
import com.projetopoo.document.Construction;
import com.projetopoo.document.Engineer;
import com.projetopoo.repository.ConstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class ConstructionService {
    @Autowired
    private ConstructionRepository repository;
    @Autowired
    private SequenceGeneratorService idService;
    private static final String SEQUENCE_NAME = Construction.SEQUENCE_NAME;

    @Autowired
    private StepService stepService;

    @PostConstruct
    public void init(){
        stepService.setConstructionService(this);
    }

    private EngineerService engineerService;
    private ClientService clientService;

    public void setEngineerService(EngineerService engineerService){
        this.engineerService = engineerService;
    }
    public void setClientService(ClientService clientService) { this.clientService = clientService; }

    public Construction create(Construction construction){
        construction.setId(idService.getSequenceNumber(SEQUENCE_NAME));
        return repository.save(construction);
    }

    public List<Construction> showConstructions(){
        return repository.findAll();
    }

    public Construction showConstruction(long constructionID){
        return repository.findById(constructionID).get();
    }

    public Construction update(Construction construction){
        Construction existingConstruction = showConstruction(construction.getId());

        existingConstruction.setName(construction.getName());
        existingConstruction.setClientID(construction.getClientID());

        return repository.save(existingConstruction);
    }

    public List<Construction> showConstructionByEngineerID(long engineerID){
        return repository.findByEngineerID(engineerID);
    }

    public String deleteAllConstructionsByEngineerID(long engineerID){
        for (Construction construction: showConstructionByEngineerID(engineerID)) {
            delete(construction.getId());
        }

        return "Deleted every Construction that has a connection with the EngineerID " + engineerID;
    }

    public String delete(long constructionID){
        repository.deleteById(constructionID);

        stepService.deleteAllStepsByConstructionID(constructionID);

        return  "The Construction with the ID " + constructionID + "was deleted.";
    }

    public Engineer getEngineer(long constructionID){
        return engineerService.showEngineer(showConstruction(constructionID).getEngineerID());
    }
    public Client getClient(long constructionID){
        return clientService.showClient(showConstruction(constructionID).getClientID());
    }
}
