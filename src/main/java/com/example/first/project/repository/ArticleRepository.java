package com.example.first.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


import com.example.first.project.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
}



