package com.gemasoftware.springjwtmysql.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user-roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRole userRoleName;

    public Role(){}

}
