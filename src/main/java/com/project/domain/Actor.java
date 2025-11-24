package com.project.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actor")
@Getter
@Setter
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Short actorId;

    private String firstName;
    private String lastName;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @ManyToMany(mappedBy = "actors")
    private Set<Film> films = new HashSet<>();
}
