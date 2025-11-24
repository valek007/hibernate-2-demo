package com.project.dao.entity;

import com.project.dao.GenericDao;
import com.project.domain.Customer;

public class CustomerDao extends GenericDao<Customer> {
    public CustomerDao() { super(Customer.class); }
}
