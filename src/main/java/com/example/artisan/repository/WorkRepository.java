package com.example.artisan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.artisan.model.po.Work;

public interface WorkRepository extends JpaRepository<Work, Long> {
	List<Work> findByAuthorId(Long authorId);
}
