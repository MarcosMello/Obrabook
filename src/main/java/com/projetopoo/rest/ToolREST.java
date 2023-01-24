package com.projetopoo.rest;

import com.projetopoo.document.Engineer;
import com.projetopoo.document.Tool;
import com.projetopoo.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ToolREST {
    @Autowired
    private ToolService service;

    @PostMapping("/tool")
    public Tool create(@RequestBody Tool tool) throws Exception{
        return service.create(tool);
    }

    @GetMapping("/tools")
    public List<Tool> showTools(){
        return service.showTools();
    }

    @GetMapping("/tool/{toolID}")
    public Tool showTool(@PathVariable long toolID){
        return service.showTool(toolID);
    }

    @GetMapping("/tool/findByEngineerID/{engineerID}")
    public List<Tool> showToolsByEngineerID(@PathVariable long engineerID){
        return service.showToolByEngineerID(engineerID);
    }

    @PutMapping("/tool")
    public Tool update(@RequestBody Tool tool){
        return service.update(tool);
    }

    @DeleteMapping("/tool/{toolID}")
    public String delete(@PathVariable long toolID){
        return service.delete(toolID);
    }

    @GetMapping("/tool/{toolID}/engineer")
    public Engineer getEngineer(@PathVariable long toolID){
        return service.getEngineer(toolID);
    }
}
