package com.projetopoo.rest;

import com.projetopoo.document.Construction;
import com.projetopoo.document.Step;
import com.projetopoo.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class StepREST {
    @Autowired
    private StepService service;

    @PostMapping("/step")
    public Step create(@RequestBody Step step) throws Exception{
        return service.create(step);
    }

    @GetMapping("/steps")
    public List<Step> showSteps(){
        return service.showSteps();
    }

    @GetMapping("/step/{stepID}")
    public Step showStep(@PathVariable long stepID){
        return service.showStep(stepID);
    }

    @GetMapping("/step/findByConstruction/{constructionID}")
    public List<Step> showStepsByConstructionID(@PathVariable long constructionID){
        return service.showStepByConstructionID(constructionID);
    }

    @PutMapping("/step")
    public Step update(@RequestBody Step step){
        return service.update(step);
    }

    @DeleteMapping("/step/{stepID}")
    public String delete(@PathVariable long stepID){
        return service.delete(stepID);
    }

    @GetMapping("/step/{stepID}/construction")
    public Construction getConstruction(@PathVariable long stepID){
        return service.getConstruction(stepID);
    }
}
