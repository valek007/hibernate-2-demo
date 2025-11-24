package com.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "city")
@Getter
@Setter
public class City {
    @Id
    @Column(name = "city_id")
    private Short cityId;

    private String city;

    @Column(name = "country_id")
    private Short countryId;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
