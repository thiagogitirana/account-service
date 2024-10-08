package com.pismo.service.account.infrastructure.repository;

import com.pismo.service.account.infrastructure.repository.model.TransactionJPA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepositoryJPA extends CrudRepository<TransactionJPA, Integer> {

    @Query(value = "select sum(t.amount) from transactions t where t.account_id = ?1", nativeQuery = true)
    Double balance(Integer accountId);
}
