package com.project.dao.entity;

import com.project.dao.GenericDao;
import com.project.domain.Inventory;

public class InventoryDao extends GenericDao<Inventory> {
    public InventoryDao() { super(Inventory.class); }
}
