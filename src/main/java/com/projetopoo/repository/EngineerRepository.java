package com.projetopoo.repository;

import com.projetopoo.document.Engineer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EngineerRepository extends MongoRepository<Engineer, Long> {
    List<Engineer> findByCnpj(String cnpj);
}
