package com.kell.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    private Integer id;
    @NotNull
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    @Type(type = "text")
    private String description;
}
