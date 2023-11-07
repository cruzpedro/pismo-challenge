package com.pismo.transaction.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "tb_transaction")
@AllArgsConstructor
@NoArgsConstructor

public class Transaction {

    @Id
    @GenericGenerator(name = "sq_transaction_id", parameters = {
            @Parameter(name = "sequence", value = "sq_transaction_id"),
            @Parameter(name = "increment_size", value = "1")
    })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_transaction_id")
    @Column(name = "id_transaction", nullable = false)
    private Long id;

    @Column(name = "dat_event", nullable = false)
    private LocalDateTime eventDate;

    @Column(name = "num_amount", nullable = false)
    private BigDecimal amount;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_operation_type", nullable = false)
    private OperationType operationType;

}
