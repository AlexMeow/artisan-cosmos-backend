package com.example.artisan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.artisan.model.dto.UserDTO;
import com.example.artisan.model.po.User;
import com.example.artisan.service.UserService;
import com.example.artisan.util.JwtTokenUtil;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // 取得所有用戶
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        List<UserDTO> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    // 依用戶ID取得用戶
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.findUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    // 新增用戶
    @PostMapping("/register")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        UserDTO newUser = userService.addUser(userDTO);
        return ResponseEntity.ok(newUser);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO loginUser) {
        UserDTO user = userService.findUserByEmail(loginUser.getEmail());
        if (user == null || !userService.checkPassword(loginUser.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
        String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(token);
    }

    // 更新用戶
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // 刪除用戶
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    // 取得用戶上傳的作品
    @GetMapping("/{id}/upload-works")
    public ResponseEntity<List<Integer>> getUserUploadWorks(@PathVariable Long id) {
        List<Integer> uploadWorks = userService.getUserUploadWorks(id);
        return ResponseEntity.ok(uploadWorks);
    }

    // 取得用戶收藏的作品
    @GetMapping("/{id}/saved-works")
    public ResponseEntity<List<Integer>> getUserSavedWorks(@PathVariable Long id) {
        List<Integer> savedWorks = userService.getUserSavedWorks(id);
        return ResponseEntity.ok(savedWorks);
    }

    // 取得用戶按讚的作品
    @GetMapping("/{id}/liked-works")
    public ResponseEntity<List<Integer>> getUserLikedWorks(@PathVariable Long id) {
        List<Integer> likedWorks = userService.getUserLikedWorks(id);
        return ResponseEntity.ok(likedWorks);
    }

    // 取得用戶的追蹤者
    @GetMapping("/{id}/followers")
    public ResponseEntity<List<Integer>> getUserFollowers(@PathVariable Long id) {
        List<Integer> followers = userService.getUserFollowers(id);
        return ResponseEntity.ok(followers);
    }

    // 取得用戶的關注者
    @GetMapping("/{id}/following")
    public ResponseEntity<List<Integer>> getUserFollowing(@PathVariable Long id) {
        List<Integer> following = userService.getUserFollowing(id);
        return ResponseEntity.ok(following);
    }
}