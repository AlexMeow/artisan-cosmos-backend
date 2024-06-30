package com.example.artisan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.artisan.model.po.Work;

public interface WorkRepository extends JpaRepository<Work, Long> {
	List<Work> findByAuthorId(Long authorId);
	
    @Query("SELECT w FROM Work w JOIN w.tags t WHERE t.name = :tagName")
    List<Work> findByTagName(@Param("tagName") String tagName);
}
