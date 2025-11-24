package com.project.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {
    @Id
    @Column(name = "customer_id")
    private Short customerId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private Boolean active;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "customer")
    private List<Rental> rentals;

    @OneToMany(mappedBy = "customer")
    private List<Payment> payments;
}
