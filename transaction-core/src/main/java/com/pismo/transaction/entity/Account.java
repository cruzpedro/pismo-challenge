package com.pismo.transaction.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Entity
@Table(name = "tb_account")
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GenericGenerator(name = "sq_post_id", parameters = {
            @Parameter(name = "sequence", value = "sq_account_id"),
            @Parameter(name = "increment_size", value = "1")
    })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_account_id")
    @Column(name = "id_account", nullable = false)
    private Long id;

    @Column(name = "int_document", nullable = false)
    private Integer document;

}
