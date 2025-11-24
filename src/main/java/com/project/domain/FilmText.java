package com.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "film_text")
@Getter
@Setter
public class FilmText {
    @Id
    @Column(name = "film_id")
    private Short filmId;

    private String title;
    private String description;
}
