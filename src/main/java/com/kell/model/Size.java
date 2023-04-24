package com.kell.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "size")
@Data
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
