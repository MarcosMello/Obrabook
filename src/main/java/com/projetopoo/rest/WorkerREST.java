package com.projetopoo.rest;

import com.projetopoo.document.Engineer;
import com.projetopoo.document.Worker;
import com.projetopoo.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WorkerREST {
    @Autowired
    private WorkerService service;

    @PostMapping("/worker")
    public Worker create(@RequestBody Worker worker) throws Exception{
        return service.create(worker);
    }

    @GetMapping("/workers")
    public List<Worker> showWorkers(){
        return service.showWorkers();
    }

    @GetMapping("/worker/{workerID}")
    public Worker showWorker(@PathVariable long workerID){
        return service.showWorker(workerID);
    }

    @GetMapping("/worker/findByEngineer/{engineerID}")
    public List<Worker> showWorkerByEngineerID(@PathVariable long engineerID){
        return service.showWorkerByEngineerID(engineerID);
    }

    @PutMapping("/worker")
    public Worker update(@RequestBody Worker worker){
        return service.update(worker);
    }

    @DeleteMapping("/worker/{workerID}")
    public String delete(@PathVariable long workerID){
        return service.delete(workerID);
    }

    @GetMapping("/worker/{workerID}/engineer")
    public Engineer getEngineer(@PathVariable long workerID){
        return service.getEngineer(workerID);
    }
}
