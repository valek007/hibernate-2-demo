package com.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {
    @Id
    @Column(name = "category_id")
    private Byte categoryId;

    private String name;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
