package com.projetopoo.service;

import com.projetopoo.document.Engineer;
import com.projetopoo.document.Supply;
import com.projetopoo.repository.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyService {
    @Autowired
    private SupplyRepository repository;
    @Autowired
    private SequenceGeneratorService idService;
    private static final String SEQUENCE_NAME = Supply.SEQUENCE_NAME;

    private EngineerService engineerService;

    public void setEngineerService(EngineerService engineerService){
        this.engineerService = engineerService;
    }

    public Supply create(Supply supply){
        supply.setId(idService.getSequenceNumber(SEQUENCE_NAME));
        return repository.save(supply);
    }

    public List<Supply> showSupplies(){
        return repository.findAll();
    }

    public Supply showSupply(long supplyID){
        return repository.findById(supplyID).get();
    }

    public Supply update(Supply supply){
        Supply existingSupply = showSupply(supply.getId());

        existingSupply.setName(supply.getName());
        existingSupply.setPricePerKg(supply.getPricePerKg());

        return repository.save(existingSupply);
    }

    public List<Supply> showSupplyByEngineerID(long engineerID){
        return repository.findByEngineerID(engineerID);
    }

    public String deleteAllSuppliesByEngineerID(long engineerID){
        for (Supply supply: showSupplyByEngineerID(engineerID)) {
            delete(supply.getId());
        }

        return "Deleted every Supply that has a connection with the EngineerID " + engineerID;
    }

    public String delete(long supplyID){
        repository.deleteById(supplyID);

        return  "The Supply with the ID " + supplyID + "was deleted.";
    }

    public Engineer getEngineer(long supplyID){
        return engineerService.showEngineer(showSupply(supplyID).getEngineerID());
    }
}
