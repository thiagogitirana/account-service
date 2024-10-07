package com.pismo.service.account.infrastructure.repository;

import com.pismo.service.account.infrastructure.repository.model.TransactionJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepositoryJPA extends CrudRepository<TransactionJPA, Integer> {
}
