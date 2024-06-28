package com.example.artisan.controller;

import com.example.artisan.model.dto.WorkDTO;
import com.example.artisan.service.WorkService;
import com.example.artisan.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/works")
public class WorkController {

    @Autowired
    private WorkService workService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadWork(@RequestBody WorkDTO workDTO, @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        Long userId = jwtTokenUtil.getUserIdFromToken(jwt);
        WorkDTO newWork = workService.addWork(workDTO, userId);
        return ResponseEntity.ok(newWork);
    }
}
