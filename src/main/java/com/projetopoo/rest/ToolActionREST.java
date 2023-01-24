package com.projetopoo.rest;

import com.projetopoo.document.Engineer;
import com.projetopoo.document.ToolAction;
import com.projetopoo.service.ToolActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ToolActionREST {
    @Autowired
    private ToolActionService service;

    @PostMapping("/toolAction")
    public ToolAction create(@RequestBody ToolAction toolAction) throws Exception{
        return service.create(toolAction);
    }

    @GetMapping("/toolActions")
    public List<ToolAction> showToolActions(){
        return service.showToolActions();
    }

    @GetMapping("/toolActions/ordered")
    public Object orderedByDate(){ return service.orderByDate(); }

    @GetMapping("/toolAction/{toolActionID}")
    public ToolAction showToolAction(@PathVariable long toolActionID){
        return service.showToolAction(toolActionID);
    }

    @GetMapping("/toolAction/findByConstruction/{constructionID}")
    public List<ToolAction> showToolActionsByConstructionID(@PathVariable long constructionID){
        return service.showToolActionByConstructionID(constructionID);
    }

    @PutMapping("/toolAction")
    public ToolAction update(@RequestBody ToolAction toolAction){
        return service.update(toolAction);
    }

    @DeleteMapping("/toolAction/{toolActionID}")
    public String delete(@PathVariable long toolActionID){
        return service.delete(toolActionID);
    }

    @GetMapping("/toolAction/{toolActionID}/engineer")
    public Engineer getEngineer(@PathVariable long toolActionID){
        return service.getEngineer(toolActionID);
    }
}
