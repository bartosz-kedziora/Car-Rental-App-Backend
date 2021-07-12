package com.kodilla.carrental.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Column(name = "EMAIL", unique = true)
    private String email;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "PHONE_NUMBER")
    private int phoneNumber;

    @Column(name = "ACCOUNT_CREATED")
    private LocalDate creationDate;

    @OneToMany(targetEntity = Rental.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "user")
    private List<Rental> rentals = new ArrayList<>();

    public User(Long id, String name, String lastName, String email, String password, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
