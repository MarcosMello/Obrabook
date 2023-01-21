package com.projetopoo.service;

import com.projetopoo.document.Engineer;
import com.projetopoo.document.User;
import com.projetopoo.repository.EngineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class EngineerService {
    @Autowired
    private EngineerRepository repository;
    @Autowired
    private SequenceGeneratorService idService;
    private static final String SEQUENCE_NAME = Engineer.SEQUENCE_NAME;

    private UserService userService;

    @Autowired
    private ConstructionService constructionService;
    @Autowired
    private SupplyActionService supplyActionService;
    @Autowired
    private SupplyService supplyService;
    @Autowired
    private ToolService toolService;
    @Autowired
    private ToolActionService toolActionService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private WorkerActionService workerActionService;

    @PostConstruct
    public void init(){
        constructionService.setEngineerService(this);
        supplyActionService.setEngineerService(this);
        supplyService.setEngineerService(this);
        toolService.setEngineerService(this);
        toolActionService.setEngineerService(this);
        workerService.setEngineerService(this);
        workerActionService.setEngineerService(this);
    }

    public void setUserService(UserService userService){
        this.userService = userService;
    }

    public Engineer create(Engineer engineer) throws Exception{
        List<Engineer> existingEngineer = showEngineersByCNPJ(engineer.getCnpj());

        if (existingEngineer.isEmpty()) {
            User updateUser = userService.showUser(engineer.getUserID());

            if (updateUser.getTypeOfUser() == null) {
                engineer.setId(idService.getSequenceNumber(SEQUENCE_NAME));

                updateUser.setTypeOfUser("Engenheiro");
                updateUser.setEngOrCliID(engineer.getId());

                userService.update(updateUser, true);

                return repository.save(engineer);
            } else {
                throw new Exception("A engineer/client is already set to this User.");
            }
        } else {
            return existingEngineer.get(0);
        }
    }

    private List<Engineer> showEngineersByCNPJ(String cnpj) {
        return repository.findByCnpj(cnpj);
    }

    public List<Engineer> showEngineers(){
        return repository.findAll();
    }

    public Engineer showEngineer(long engineerID){
        return repository.findById(engineerID).get();
    }

    public Engineer update(Engineer engineer){
        Engineer existingEngineer = showEngineer(engineer.getId());

        existingEngineer.setCnpj(engineer.getCnpj());
        existingEngineer.setName(engineer.getName());
        existingEngineer.setPhoneNo(engineer.getPhoneNo());

        return repository.save(existingEngineer);
    }

    public String delete(long engineerID){
        Engineer engineerTBD = showEngineer(engineerID);

        repository.deleteById(engineerID);

        String msg = userService.delete(engineerTBD.getUserID());

        msg += "\n" + constructionService.deleteAllConstructionsByEngineerID(engineerTBD.getUserID());
        msg += "\n" + supplyActionService.deleteAllSupplyActionsByEngineerID(engineerTBD.getUserID());
        msg += "\n" + supplyService.deleteAllSuppliesByEngineerID(engineerTBD.getUserID());
        msg += "\n" + toolService.deleteAllToolsByEngineerID(engineerTBD.getUserID());
        msg += "\n" + toolActionService.deleteAllToolActionsByEngineerID(engineerTBD.getUserID());
        msg += "\n" + workerService.deleteAllWorkersByEngineerID(engineerTBD.getUserID());
        msg += "\n" + workerActionService.deleteAllWorkerActionsByEngineerID(engineerTBD.getUserID());

        return msg + "\n" + "The engineer with the ID " + engineerID + "was deleted!";
    }

    public User getUser(long engineerID){
        return userService.showUser(showEngineer(engineerID).getUserID());
    }
}
