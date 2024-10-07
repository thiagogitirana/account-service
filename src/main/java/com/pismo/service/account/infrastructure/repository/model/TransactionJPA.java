package com.pismo.service.account.infrastructure.repository.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TRANSACTIONS")
public class TransactionJPA implements Serializable {
    @Id
    @Column(name = "TRANSACTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private AccountJPA account;

    @ManyToOne
    @JoinColumn(name = "OPERATION_TYPE_ID")
    private OperationTypeJPA operationType;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "EVENT_DATE")
    @CreationTimestamp
    private LocalDateTime eventDate;

}
