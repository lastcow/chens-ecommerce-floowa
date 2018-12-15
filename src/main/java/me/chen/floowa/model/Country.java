package me.chen.floowa.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Country {

    @Id
    private String code;

    @Column(unique = true)
    private String name;
}
