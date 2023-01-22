package com.projetopoo.rest;

import com.projetopoo.document.Construction;
import com.projetopoo.document.Engineer;
import com.projetopoo.document.Supply;
import com.projetopoo.document.SupplyAction;
import com.projetopoo.service.SupplyActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SupplyActionREST {
    @Autowired
    private SupplyActionService service;

    @PostMapping("/supplyAction")
    public SupplyAction create(@RequestBody SupplyAction supplyAction) throws Exception{
        return service.create(supplyAction);
    }

    @GetMapping("/supplyActions")
    public List<SupplyAction> showSupplyActions(){
        return service.showSupplyActions();
    }

    @GetMapping("/supplyAction/{supplyActionID}")
    public SupplyAction showSupplyAction(@PathVariable long supplyActionID){
        return service.showSupplyAction(supplyActionID);
    }

    @GetMapping("/supplyAction/findByConstruction/{constructionID}")
    public List<SupplyAction> showSupplyActionsByConstructionID(@PathVariable long constructionID){
        return service.showSupplyActionByConstructionID(constructionID);
    }

    @PutMapping("/supplyAction")
    public SupplyAction update(@RequestBody SupplyAction supplyAction){
        return service.update(supplyAction);
    }

    @GetMapping("/supplyActions/ordered")
    public Object orderedByDate(){ return service.orderByDate(); }

    @DeleteMapping("/supplyAction/{supplyActionID}")
    public String delete(@PathVariable long supplyActionID){
        return service.delete(supplyActionID);
    }

    @GetMapping("/supplyAction/{supplyActionID}/engineer")
    public Engineer getEngineer(@PathVariable long supplyActionID){
        return service.getEngineer(supplyActionID);
    }
    @GetMapping("/supplyAction/{supplyActionID}/supply")
    public Supply getSupply(@PathVariable long supplyActionID){
        return service.getSupply(supplyActionID);
    }
    @GetMapping("/supplyAction/{supplyActionID}/construction")
    public Construction getConstruction(@PathVariable long supplyActionID){
        return service.getConstruction(supplyActionID);
    }
}
