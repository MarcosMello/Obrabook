package com.projetopoo.rest;

import com.projetopoo.document.Engineer;
import com.projetopoo.document.Supply;
import com.projetopoo.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SupplyREST {
    @Autowired
    private SupplyService service;

    @PostMapping("/supply")
    public Supply create(@RequestBody Supply supply) throws Exception{
        return service.create(supply);
    }

    @GetMapping("/supplies")
    public List<Supply> showSupplies(){
        return service.showSupplies();
    }

    @GetMapping("/supply/{supplyID}")
    public Supply showSupply(@PathVariable long supplyID){
        return service.showSupply(supplyID);
    }

    @GetMapping("/supply/findByEngineer/{engineerID}")
    public List<Supply> showSuppliesByEngineerID(@PathVariable long engineerID){
        return service.showSupplyByEngineerID(engineerID);
    }

    @PutMapping("/supply")
    public Supply update(@RequestBody Supply supply){
        return service.update(supply);
    }

    @DeleteMapping("/supply/{supplyID}")
    public String delete(@PathVariable long supplyID){
        return service.delete(supplyID);
    }

    @GetMapping("/supply/{supplyID}/engineer")
    public Engineer getEngineer(@PathVariable long supplyID){
        return service.getEngineer(supplyID);
    }
}
