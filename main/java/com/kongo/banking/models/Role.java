package com.kongo.banking.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor

@Entity
public class Role extends AbstractEntity {



    private  String name;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
