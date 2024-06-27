package com.example.artisan.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.artisan.model.dto.UserDTO;
import com.example.artisan.model.po.User;
import com.example.artisan.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    public UserDTO addUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        // Encrypt password before saving. Salted hashed.
        String hashedPassword = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashedPassword);
        user.setRole("USER"); // 預設角色為 USER
        User newUser = userRepository.save(user);
        return convertToDTO(newUser);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setBio(userDTO.getBio());
        // 不更新 role 和 password
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public List<Integer> getUserUploadWorks(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUploadWorks();
    }

    public List<Integer> getUserSavedWorks(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getSavedWorks();
    }

    public List<Integer> getUserLikedWorks(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getLikedWorks();
    }

    public List<Integer> getUserFollowers(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFollowers();
    }

    public List<Integer> getUserFollowing(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFollowing();
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getBio(),
                user.getCreatedDate(),
                user.getUploadWorks(),
                user.getSavedWorks(),
                user.getLikedWorks(),
                user.getFollowers(),
                user.getFollowing()
        );
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setBio(userDTO.getBio());
        user.setPassword(userDTO.getPassword()); // 這裡先設置明文密碼，稍後進行salted hash
        return user;
    }
}