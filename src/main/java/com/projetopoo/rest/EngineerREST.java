package com.projetopoo.rest;

import com.projetopoo.document.Engineer;
import com.projetopoo.document.User;
import com.projetopoo.service.EngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EngineerREST {
    @Autowired
    private EngineerService service;

    @PostMapping("/engineer")
    public Engineer create(@RequestBody Engineer engineer) throws Exception{
        return service.create(engineer);
    }

    @GetMapping("/engineers")
    public List<Engineer> showEngineers(){
        return service.showEngineers();
    }

    @GetMapping("/engineer/cnpj/{cnpj}")
    public List<Engineer> showEngineersByCNPJ(@PathVariable String cnpj) { return service.showEngineersByCNPJ(cnpj); }

    @GetMapping("/engineer/{engineerID}")
    public Engineer showEngineer(@PathVariable long engineerID){
        return service.showEngineer(engineerID);
    }

    @GetMapping("/engineer/{engineerID}/user")
    public User showUser(@PathVariable long engineerID){ return service.getUser(engineerID); }

    @PutMapping("/engineer")
    public Engineer update(@RequestBody Engineer engineer){
        return service.update(engineer);
    }

    @DeleteMapping("/engineer/{engineerID}")
    public String delete(@PathVariable long engineerID){
        return service.delete(engineerID);
    }
}
