package com.pismo.service.account.infrastructure.repository.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "TRANSACTIONS")
public class TransactionJPA implements Serializable {
    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT")
    private AccountJPA account;

    @ManyToOne
    @JoinColumn(name = "OPERATION_TYPE")
    private OperationTypeJPA operationType;



}
