package com.projetopoo.rest;

import com.projetopoo.document.Engineer;
import com.projetopoo.document.WorkerAction;
import com.projetopoo.service.WorkerActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class WorkerActionREST {
    @Autowired
    private WorkerActionService service;

    @PostMapping("/workerAction")
    public WorkerAction create(@RequestBody WorkerAction workerAction) throws Exception{
        return service.create(workerAction);
    }

    @GetMapping("/workerActions")
    public List<WorkerAction> showWorkerActions(){
        return service.showWorkerActions();
    }

    @GetMapping("/workerActions/ordered")
    public Object orderedByDate(){ return service.orderByDate(); }

    @GetMapping("/workerAction/{workerActionID}")
    public WorkerAction showWorkerAction(@PathVariable long workerActionID){
        return service.showWorkerAction(workerActionID);
    }

    @GetMapping("/workerAction/findByConstruction/{constructionID}")
    public List<WorkerAction> showWorkerActionsByConstructionID(@PathVariable long constructionID){
        return service.showWorkerActionByConstructionID(constructionID);
    }

    @PutMapping("/workerAction")
    public WorkerAction update(@RequestBody WorkerAction workerAction){
        return service.update(workerAction);
    }

    @DeleteMapping("/workerAction/{workerActionID}")
    public String delete(@PathVariable long workerActionID){
        return service.delete(workerActionID);
    }

    @GetMapping("/workerAction/{workerActionID}/engineer")
    public Engineer getEngineer(@PathVariable long workerActionID){
        return service.getEngineer(workerActionID);
    }
}
