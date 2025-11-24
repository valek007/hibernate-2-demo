package com.project.dao.entity;

import com.project.dao.GenericDao;
import com.project.domain.Address;

public class AddressDao extends GenericDao<Address> {
    public AddressDao() { super(Address.class); }
}
