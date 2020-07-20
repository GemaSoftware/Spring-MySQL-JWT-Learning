package com.gemasoftware.springjwtmysql.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "user-table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 24)
    private String userName;

    @NotBlank
    @Size(max = 128)
    private String password;

    @NotBlank
    @Size(max = 75)
    @Email
    private String userEmail;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "user-roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(){}

    public User(String userName, String userEmail, String password){
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;

    }


}
