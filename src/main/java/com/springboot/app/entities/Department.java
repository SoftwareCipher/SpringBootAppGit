package com.springboot.app.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "description")
    private String desc;

    public Department() {
    }

    public Department(long id, String desc) {
        this.id = id;
        this.desc = desc;
    }
}
