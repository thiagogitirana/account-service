package com.pismo.service.account.infrastructure.repository;

import com.pismo.service.account.infrastructure.repository.model.AccountJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepositoryJPA extends CrudRepository<AccountJPA, Integer> {

    boolean existsByDocumentNumber(String documentNumber);
}
