package com.project.dao.entity;

import com.project.dao.GenericDao;
import com.project.domain.Country;

public class CountryDao extends GenericDao<Country> {
    public CountryDao() { super(Country.class); }
}
