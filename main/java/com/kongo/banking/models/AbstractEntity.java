package com.kongo.banking.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public class AbstractEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(
            name = "createDate",
            nullable = false,
            updatable = false
    )
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(name = "lastModifiedDate")
    private LocalDateTime lastUpdated;

}
