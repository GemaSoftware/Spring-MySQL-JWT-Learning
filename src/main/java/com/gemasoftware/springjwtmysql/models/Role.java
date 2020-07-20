package com.gemasoftware.springjwtmysql.models;


import javax.persistence.*;

@Entity
@Table(name = "user-roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRole userRoleName;

    public Role(){}

    public Role(UserRole userRoleName){
        this.userRoleName = userRoleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(UserRole userRoleName) {
        this.userRoleName = userRoleName;
    }
}
