package com.projetopoo.service;

import com.projetopoo.document.User;
import com.projetopoo.repository.ClientRepository;
import com.projetopoo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private EngineerService engineerService;

    @Autowired
    private SequenceGeneratorService idService;
    private static final String SEQUENCE_NAME = User.SEQUENCE_NAME;

    @PostConstruct
    public void init(){
        clientService.setUserService(this);
        engineerService.setUserService(this);
    }

    public User create(User user){
        List<User> existingUser = showUsersByEmail(user.getEmail());

        if (existingUser.isEmpty()){
            user.setId(idService.getSequenceNumber(SEQUENCE_NAME));

            return repository.save(user);
        } else {
            return existingUser.get(0);
        }
    }

    public List<User> showUsers(){
        return repository.findAll();
    }

    public List<User> showUsersByEmail(String email){ return repository.findByEmail(email); }

    public User showUser(long userID){
        return repository.findById(userID).get();
    }

    public User update(User user, boolean includeType){
        User existingUser = showUser(user.getId());

        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        if (includeType){
            existingUser.setTypeOfUser(user.getTypeOfUser());
            existingUser.setEngOrCliID(user.getEngOrCliID());
        }

        return repository.save(existingUser);
    }

    public User update(User user){
        return update(user, false);
    }

    public String delete(long userID){
        User userTBD = showUser(userID);

        String rsp = "The user associated with the ID " + userID + "was deleted!";

        repository.deleteById(userID);

        if (userTBD.getTypeOfUser().equals("Engenheiro")){
            rsp += "\n" + engineerService.delete(userTBD.getEngOrCliID());
        } else if (userTBD.getTypeOfUser().equals("Cliente")){
            rsp += "\n" + clientService.delete(userTBD.getEngOrCliID());
        }

        return rsp;
    }
}
