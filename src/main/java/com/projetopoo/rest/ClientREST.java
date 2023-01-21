package com.projetopoo.rest;

import com.projetopoo.document.Client;
import com.projetopoo.document.User;
import com.projetopoo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientREST {
    @Autowired
    private ClientService service;

    @PostMapping("/client")
    public Client create(@RequestBody Client client) throws Exception{
        return service.create(client);
    }

    @GetMapping("/clients")
    public List<Client> showClients(){
        return service.showClients();
    }

    @GetMapping("/client/{clientID}")
    public Client showClient(@PathVariable long clientID){
        return service.showClient(clientID);
    }

    @GetMapping("/client/{clientID}/user")
    public User showUser(@PathVariable long clientID){ return service.getUser(clientID); }

    @DeleteMapping("/client/{clientID}")
    public String delete(@PathVariable long clientID){
        return service.delete(clientID);
    }
}
