package com.project.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "staff")
@Getter
@Setter
public class Staff {
    @Id
    @Column(name = "staff_id")
    private Byte staffId;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String picture;
    private String email;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private Boolean active;
    private String username;
    private String password;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "staff")
    private List<Rental> rentals;

    @OneToMany(mappedBy = "staff")
    private List<Payment> payments;
}
