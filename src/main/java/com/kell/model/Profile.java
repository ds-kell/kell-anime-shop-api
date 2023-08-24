package com.kell.model;


import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
/**
 * private String firstname;
 * private String lastname;
 */
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private Date dob;
    private String gender;
    @Column(name = "avatar")
    @Type(type = "text")
    private String avatarUrl;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
