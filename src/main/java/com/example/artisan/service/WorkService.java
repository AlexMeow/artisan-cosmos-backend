package com.example.artisan.service;

import com.example.artisan.model.dto.ArtistDTO;
import com.example.artisan.model.dto.UserDTO;
import com.example.artisan.model.dto.WorkDTO;
import com.example.artisan.model.po.Tag;
import com.example.artisan.model.po.User;
import com.example.artisan.model.po.Work;
import com.example.artisan.repository.TagRepository;
import com.example.artisan.repository.UserRepository;
import com.example.artisan.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    public WorkDTO addWork(WorkDTO workDTO, Long userId) {
        // Find author
        User author = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Handle tags
        Set<Tag> tags = workDTO.getTags().stream()
                .map(tagName -> {
                    Tag tag = tagRepository.findByName(tagName);
                    if (tag == null) {
                        tag = new Tag();
                        tag.setName(tagName);
                        tag = tagRepository.save(tag);
                    }
                    return tag;
                })
                .collect(Collectors.toSet());

        // Create entity
        Work work = new Work();
        work.setName(workDTO.getName());
        work.setDescription(workDTO.getDescription());
        work.setImgUrls(workDTO.getImgUrls());
        work.setTags(tags);  // Assuming you have a tags field in Work entity that accepts a List<Tag>
        work.setAuthor(author);

        // Save work
        Work savedWork = workRepository.save(work);

        // Return DTO
        return convertToDTO(savedWork);
    }
    
    public List<WorkDTO> getWorksByAuthorId(Long authorId) {
        List<Work> works = workRepository.findByAuthorId(authorId);
        return works.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    
    public List<WorkDTO> getAllArtworks() {
    		List<Work> works = workRepository.findAll();
        return works.stream().map(this::convertToDTO).collect(Collectors.toList());

	}

    private WorkDTO convertToDTO(Work work) {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setId(work.getId());
        workDTO.setName(work.getName());
        workDTO.setDescription(work.getDescription());
        workDTO.setImgUrls(work.getImgUrls());
        workDTO.setTags(work.getTags().stream().map(Tag::getName).collect(Collectors.toList()));
        workDTO.setAuthorId(work.getAuthor().getId());
        
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setId(work.getAuthor().getId());
        artistDTO.setName(work.getAuthor().getName());
        artistDTO.setBio(work.getAuthor().getBio());
        artistDTO.setAvatarUrl(work.getAuthor().getAvatarUrl());
        artistDTO.setCreatedDate(work.getAuthor().getCreatedDate());
        artistDTO.setJobTitle(work.getAuthor().getJobTitle());
        workDTO.setArtist(artistDTO);
        
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(work.getAuthor().getId());
//        userDTO.setName(work.getAuthor().getName());
//        userDTO.setBio(work.getAuthor().getBio());
//        userDTO.setAvatarUrl(work.getAuthor().getAvatarUrl());
//        workDTO.setArtist(userDTO);
        
        return workDTO;
    }
}
