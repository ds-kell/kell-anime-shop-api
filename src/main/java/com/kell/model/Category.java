package com.kell.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @ManyToOne
//    @JoinColumn(name = "parent_id")
//    private Category categoryParent;
    private String name;
}
