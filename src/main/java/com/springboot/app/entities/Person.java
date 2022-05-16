package com.springboot.app.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "fio")
    private String fio;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private long phoneNumber;

    public Person() {
    }

    public Person(long id, String fio, String email, long phoneNumber) {
        this.id = id;
        this.fio = fio;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
