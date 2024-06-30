package com.example.artisan.controller;

import com.example.artisan.model.dto.WorkDTO;
import com.example.artisan.service.WorkService;
import com.example.artisan.util.JwtTokenUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/works")
public class WorkController {

    @Autowired
    private WorkService workService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // 上傳作品
    @PostMapping("/upload")
    public ResponseEntity<?> uploadWork(@RequestBody WorkDTO workDTO, @RequestHeader("Authorization") String token) {
        // 驗證JWT
        if (!jwtTokenUtil.validateToken(token.substring(7))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    		// 從jwt裡面解析出上傳者的user id
        String jwt = token.substring(7);
        Long userId = jwtTokenUtil.getUserIdFromToken(jwt);
        WorkDTO newWork = workService.addWork(workDTO, userId);
        return ResponseEntity.ok(newWork);
    }
    
    // 取得指定用戶的所有作品
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<WorkDTO>> getWorksByAuthorId(@PathVariable Long authorId) {
        List<WorkDTO> works = workService.getWorksByAuthorId(authorId);
        return ResponseEntity.ok(works);
    }
    
    // 取得所有作品
    @GetMapping("/get-all-works")
    public ResponseEntity<List<WorkDTO>> getAllWorks() {
        List<WorkDTO> works = workService.getAllArtworks();
        return ResponseEntity.ok(works);
    }
    
    // 修改作品
    @PutMapping("/update/{id}")
    public ResponseEntity<WorkDTO> updateWork(@PathVariable Long id, @RequestBody WorkDTO workDTO, @RequestHeader("Authorization") String token) {
        // 驗證JWT
        if (!jwtTokenUtil.validateToken(token.substring(7))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            WorkDTO updatedWork = workService.updateWork(id, workDTO);
            return ResponseEntity.ok(updatedWork);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // 刪除作品
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWorkById (@PathVariable Long id, @RequestHeader("Authorization") String token) {
        // 驗證JWT
        if (!jwtTokenUtil.validateToken(token.substring(7))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        workService.deleteWorkById(id);
        return ResponseEntity.noContent().build();
    }
    
}
