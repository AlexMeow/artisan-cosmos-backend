package com.example.artisan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.artisan.model.po.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
