package com.kell.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "discount")
@Data
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private float value;
    @Column(name = "start_date")
    private Instant startDate;
    @Column(name = "end_date")
    private Instant endDate;
}
