package com.projetopoo.service;

import com.projetopoo.document.Engineer;
import com.projetopoo.document.Tool;
import com.projetopoo.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolService {
    @Autowired
    private ToolRepository repository;
    @Autowired
    private SequenceGeneratorService idService;
    private static final String SEQUENCE_NAME = Tool.SEQUENCE_NAME;

    private EngineerService engineerService;

    public void setEngineerService(EngineerService engineerService){
        this.engineerService = engineerService;
    }

    public Tool create(Tool tool){
        tool.setId(idService.getSequenceNumber(SEQUENCE_NAME));
        tool.setAssigned(false);
        return repository.save(tool);
    }

    public List<Tool> showTools(){
        return repository.findAll();
    }

    public Tool showTool(long toolID){
        return repository.findById(toolID).get();
    }

    public Tool update(Tool tool){
        Tool existingTool = showTool(tool.getId());

        existingTool.setName(tool.getName());
        existingTool.setBrand(tool.getBrand());
        existingTool.setPrice(tool.getPrice());
        existingTool.setAverageLifeSpan(tool.getAverageLifeSpan());
        existingTool.setProvider(tool.getProvider());

        return repository.save(existingTool);
    }

    public List<Tool> showToolByEngineerID(long engineerID){
        return repository.findByEngineerID(engineerID);
    }

    public String deleteAllToolsByEngineerID(long engineerID){
        for (Tool tool: showToolByEngineerID(engineerID)) {
            delete(tool.getId());
        }

        return "Deleted every Tool that has a connection with the EngineerID " + engineerID;
    }

    public String delete(long toolID){
        repository.deleteById(toolID);

        return  "The Tool with the ID " + toolID + "was deleted.";
    }

    public Engineer getEngineer(long toolID){
        return engineerService.showEngineer(showTool(toolID).getEngineerID());
    }
}
