package com.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "country")
@Getter
@Setter
public class Country {
    @Id
    @Column(name = "country_id")
    private Short countryId;

    private String country;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
