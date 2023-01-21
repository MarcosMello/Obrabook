package com.projetopoo.service;

import com.projetopoo.document.Step;
import com.projetopoo.repository.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StepService {
    @Autowired
    private StepRepository repository;
    @Autowired
    private SequenceGeneratorService idService;
    private static final String SEQUENCE_NAME = Step.SEQUENCE_NAME;

    @Autowired
    private ConstructionService constructionService;

    public Step create(Step step){
        step.setId(idService.getSequenceNumber(SEQUENCE_NAME));
        return repository.save(step);
    }

    public List<Step> showSupplies(){
        return repository.findAll();
    }

    public Step showStep(long stepID){
        return repository.findById(stepID).get();
    }

    public Step update(Step step){
        Step existingStep = showStep(step.getId());

        existingStep.setName(step.getName());
        existingStep.setExpectedValue(step.getExpectedValue());
        existingStep.setActualValue(step.getActualValue());
        existingStep.setComplete(step.getIsComplete());

        return repository.save(existingStep);
    }

    public List<Step> showStepByConstructionID(long constructionID){
        return repository.findByConstructionID(constructionID);
    }

    public String deleteAllStepsByConstructionID(long constructionID){
        for (Step steps: showStepByConstructionID(constructionID)) {
            delete(steps.getId());
        }

        return "Deleted every Step that has a connection with the EngineerID " + constructionID;
    }

    public String delete(long constructionID){
        repository.deleteById(constructionID);

        return  "The Step with the ID " + constructionID + "was deleted.";
    }
}
