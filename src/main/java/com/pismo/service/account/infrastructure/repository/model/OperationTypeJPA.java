package com.pismo.service.account.infrastructure.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "OPERATION_TYPE")
public class OperationTypeJPA implements Serializable {
    @Id
    @Column(name = "OPERATION_TYPE_ID", nullable = false)
    private Integer operationTypeId;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
}
