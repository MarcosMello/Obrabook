package com.projetopoo.rest;

import com.projetopoo.document.Client;
import com.projetopoo.document.Construction;
import com.projetopoo.document.Engineer;
import com.projetopoo.service.ConstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ConstructionREST {
    @Autowired
    private ConstructionService service;

    @PostMapping("/construction")
    public Construction create(@RequestBody Construction construction) throws Exception{
        return service.create(construction);
    }

    @GetMapping("/constructions")
    public List<Construction> showConstructions(){
        return service.showConstructions();
    }

    @GetMapping("/construction/{constructionID}")
    public Construction showConstruction(@PathVariable long constructionID){
        return service.showConstruction(constructionID);
    }

    @GetMapping("/construction/findByEngineer/{engineerID}")
    public List<Construction> showConstructionsByEngineerID(@PathVariable long engineerID){
        return service.showConstructionByEngineerID(engineerID);
    }

    @PutMapping("/construction")
    public Construction update(@RequestBody Construction construction){
        return service.update(construction);
    }

    @DeleteMapping("/construction/{constructionID}")
    public String delete(@PathVariable long constructionID){
        return service.delete(constructionID);
    }

    @GetMapping("/construction/{constructionID}/engineer")
    public Engineer getEngineer(@PathVariable long constructionID){
        return service.getEngineer(constructionID);
    }
    @GetMapping("/construction/{constructionID}/client")
    public Client getClient(@PathVariable long constructionID){
        return service.getClient(constructionID);
    }
}
