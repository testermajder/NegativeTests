package com.projectname.api.tests.data.db.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "example", name = "users")
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user")
    private String user;

    @Column(name = "email")
    private String email;

    @Column(name = "team")
    private String team;

    @Column(name = "isActive")
    private int isActive;

    @Column(name = "registrationDate", columnDefinition = "DATE")
    private Date registrationDate;


    public Example() {

    }

    public Example(String user, String email, String team, int isActive, Date registrationDate) {
        super();
        this.user = user;
        this.email = email;
        this.team = team;
        this.isActive = isActive;
        this.registrationDate = registrationDate;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", email='" + email + '\'' +
                ", team='" + team + '\'' +
                ", isActive='" + isActive + '\'' +
                ", getRegistrationDate='" + registrationDate + '\'' +
                '}';
    }
}

