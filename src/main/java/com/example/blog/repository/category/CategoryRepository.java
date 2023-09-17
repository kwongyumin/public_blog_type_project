package com.example.blog.repository.category;

import com.example.blog.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long>, CategoryCustomRepository {
}
