package com.pismo.service.account.infrastructure.repository;

import com.pismo.service.account.infrastructure.repository.model.OperationTypeJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepositoryJPA extends CrudRepository<OperationTypeJPA, Integer> {
}
