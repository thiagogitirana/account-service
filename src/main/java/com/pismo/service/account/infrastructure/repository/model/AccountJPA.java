package com.pismo.service.account.infrastructure.repository.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Data
@Entity
@Table(name = "ACCOUNT")
public class AccountJPA implements Serializable {

    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @Column(name = "DOCUMENT_NUMBER", nullable = false)
    private String documentNumber;

    @OneToMany(mappedBy = "account", cascade = ALL, fetch = LAZY)
    private List<TransactionJPA> transactions;

    @Column(name = "CREATED_AT")
    @CreationTimestamp
    private LocalDateTime createdAt;

}
