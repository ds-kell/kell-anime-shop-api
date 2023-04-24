package com.kell.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NotNull
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    private Integer id;
//    private String firstname;
//    private String lastname;
    private String fullname;
    @Column(name = "phone_number")
    private String phoneNumber;
    private Date dob;
    private String gender;
    @Column(name = "avatar")
    private String avatarUrl;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
