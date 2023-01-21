package com.projetopoo.repository;

import com.projetopoo.document.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClientRepository extends MongoRepository<Client, Long> {
    List<Client> findByCpf(String cpf);
}
