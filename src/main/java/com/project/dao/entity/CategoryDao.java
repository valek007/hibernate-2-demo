package com.project.dao.entity;

import com.project.dao.GenericDao;
import com.project.domain.Address;
import com.project.domain.Category;

public class CategoryDao extends GenericDao<Category> {
    public CategoryDao() { super(Category.class); }
}
