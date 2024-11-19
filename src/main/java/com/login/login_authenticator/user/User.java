package com.login.login_authenticator.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // UUID para id unico
    private String id;
    private String email;
    private String name;
    private String password;

}
