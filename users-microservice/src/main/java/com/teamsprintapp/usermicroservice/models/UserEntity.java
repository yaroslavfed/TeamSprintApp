package com.teamsprintapp.usermicroservice.models;

import com.teamsprintapp.usermicroservice.enums.Role;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import org.aspectj.lang.annotation.RequiredTypes;

import java.util.UUID;

/**
 * Модель пользователя
 */
@Entity
@Table(name = "userinfo")
public class UserEntity {
    //region Fields
    /**
     * id пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * Логин пользователя, он же его email
     */
    private String login;

    /**
     * Хеш пароля пользователя
     */
    private String password;

    /**
     * Имя пользователя
     */
    private String firstname;

    /**
     * Фамилия пользователя
     */
    private String lastname;

    /**
     * Роль пользователя на сайте
     */
    @Enumerated(EnumType.STRING)
    private Role role;
    //endregion Fields

    //region Getters
    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Role getRole() {
        return role;
    }
    //endregion Getters

    //region Setters
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    //endregion Setters
}
