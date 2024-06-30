package com.example.artisan.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.artisan.model.dto.TagDTO;
import com.example.artisan.model.po.Tag;
import com.example.artisan.repository.TagRepository;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    // 撈取所有 Tags
    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        List<TagDTO> tagDTOs = tags.stream().map(tag -> new TagDTO(tag.getId(), tag.getName())).collect(Collectors.toList());
        return ResponseEntity.ok(tagDTOs);
    }
}
