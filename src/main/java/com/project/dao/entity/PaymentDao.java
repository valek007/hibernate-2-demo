package com.project.dao.entity;

import com.project.dao.GenericDao;
import com.project.domain.Payment;

public class PaymentDao extends GenericDao<Payment> {
    public PaymentDao() { super(Payment.class); }
}
