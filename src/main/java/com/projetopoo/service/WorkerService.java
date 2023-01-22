package com.projetopoo.service;

import com.projetopoo.document.Engineer;
import com.projetopoo.document.Worker;
import com.projetopoo.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepository repository;
    @Autowired
    private SequenceGeneratorService idService;
    private static final String SEQUENCE_NAME = Worker.SEQUENCE_NAME;

    private EngineerService engineerService;

    public void setEngineerService(EngineerService engineerService){
        this.engineerService = engineerService;
    }

    public Worker create(Worker worker){
        worker.setId(idService.getSequenceNumber(SEQUENCE_NAME));
        worker.setAssigned(false);
        return repository.save(worker);
    }

    public List<Worker> showWorkers(){
        return repository.findAll();
    }

    public Worker showWorker(long workerID){
        return repository.findById(workerID).get();
    }

    public Worker update(Worker worker){
        Worker existingWorker = showWorker(worker.getId());

        existingWorker.setFirstName(worker.getFirstName());
        existingWorker.setLastName(worker.getLastName());
        existingWorker.setDailyWage(worker.getDailyWage());
        existingWorker.setPhoneNo(worker.getPhoneNo());
        existingWorker.setType(worker.getType());
        existingWorker.setStatus(worker.getStatus());
        existingWorker.setCpf(worker.getCpf());
        existingWorker.setAge(worker.getAge());
        existingWorker.setStreet(worker.getStreet());
        existingWorker.setDistrict(worker.getDistrict());
        existingWorker.setNumber(worker.getNumber());
        existingWorker.setZip_code(worker.getZip_code());

        return repository.save(existingWorker);
    }

    public List<Worker> showWorkerByEngineerID(long engineerID){
        return repository.findByEngineerID(engineerID);
    }

    public String deleteAllWorkersByEngineerID(long engineerID){
        for (Worker worker: showWorkerByEngineerID(engineerID)) {
            delete(worker.getId());
        }

        return "Deleted every Worker that has a connection with the EngineerID " + engineerID;
    }

    public String delete(long workerID){
        repository.deleteById(workerID);

        return  "The Worker with the ID " + workerID + "was deleted.";
    }

    public Engineer getEngineer(long workerID){
        return engineerService.showEngineer(showWorker(workerID).getEngineerID());
    }
}
