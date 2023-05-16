package com.kell.service;

import com.kell.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoriesWithoutParent();

    List<Category> getCategoriesByParentId(Integer parentId);

//    void addCategory(CategoryDto categoryDto);

    void removeCategory(Integer categoryId);
}
