package com.example.artisan.service;

import com.example.artisan.model.dto.WorkDTO;
import com.example.artisan.model.po.User;
import com.example.artisan.model.po.Work;
import com.example.artisan.repository.UserRepository;
import com.example.artisan.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private UserRepository userRepository;

    public WorkDTO addWork(WorkDTO workDTO, Long userId) {
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Work work = new Work();
        work.setName(workDTO.getName());
        work.setDescription(workDTO.getDescription());
        work.setTags(workDTO.getTags());
        work.setImgUrls(workDTO.getImgUrls());
        work.setAuthor(author);

        Work newWork = workRepository.save(work);
        return convertToDTO(newWork);
    }

    private WorkDTO convertToDTO(Work work) {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setId(work.getId());
        workDTO.setName(work.getName());
        workDTO.setDescription(work.getDescription());
        workDTO.setTags(work.getTags());
        workDTO.setImgUrls(work.getImgUrls());
        workDTO.setAuthorId(work.getAuthor().getId());
        return workDTO;
    }
}
