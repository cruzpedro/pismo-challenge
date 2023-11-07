package com.pismo.transaction.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Builder
@Entity
@Table(name = "tb_operation_type")
@AllArgsConstructor
@NoArgsConstructor
public class OperationType {

    @Id
    @GenericGenerator(name = "sq_operation_type_id", parameters = {
            @Parameter(name = "sequence", value = "sq_operation_type_id"),
            @Parameter(name = "increment_size", value = "1")
    })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_operation_type_id")
    @Column(name = "id_operation_type", nullable = false)
    private Long id;

    @Column(name = "txt_description", nullable = false)
    private String description;

}
