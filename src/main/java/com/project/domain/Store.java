package com.project.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "store")
@Getter
@Setter
public class Store {
    @Id
    @Column(name = "store_id")
    private Byte storeId;

    @ManyToOne
    @JoinColumn(name = "manager_staff_id")
    private Staff manager;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "store")
    private List<Customer> customers;

    @OneToMany(mappedBy = "store")
    private List<Inventory> inventories;
}
