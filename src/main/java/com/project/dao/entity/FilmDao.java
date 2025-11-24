package com.project.dao.entity;

import com.project.dao.GenericDao;
import com.project.domain.Film;

public class FilmDao extends GenericDao<Film> {
    public FilmDao() { super(Film.class); }
}
