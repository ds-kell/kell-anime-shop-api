package com.kell.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NotNull
@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	@JsonIgnore
	@NotNull
	@Column(name = "password_hash", nullable = false)
	private String password;
	@Email
	@Column(name = "email", unique = true)
	private String email;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "account_authorities",
			joinColumns = @JoinColumn(name = "account_id"),
			inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private Set<Authority> authorities = new HashSet<>();
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
	@JsonIgnore
	private Profile profile;
	@Column(name="create_date")
	private Date createDate;
}
