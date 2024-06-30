package com.example.artisan.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.artisan.model.dto.ArtistDTO;
import com.example.artisan.model.dto.UserDTO;
import com.example.artisan.model.po.User;
import com.example.artisan.model.po.UserFollowing;
import com.example.artisan.repository.UserFollowingRepository;
import com.example.artisan.repository.UserRepository;

import io.jsonwebtoken.io.IOException;
import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserFollowingRepository userFollowingRepository;

	@Value("${default.avatar}")
	private String DEFAULT_AVATAR_URL;

	public List<UserDTO> findAllUsers() {
		return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	// 跟findAllUsers的差異是，findAllArtists return
	// ArtistDTO，相較於UserDTO，不會有email或password等機敏性資料。
	public List<ArtistDTO> findAllArtists() {
		return userRepository.findAll().stream().map(this::convertToArtistDTO).collect(Collectors.toList());
	}

	public UserDTO findUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		return convertToDTO(user);
	}

	// 跟findUserById的差異同findAllArtists
	public ArtistDTO findArtistById(Long id) {
		User artist = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		return convertToArtistDTO(artist);
	}

	public UserDTO findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user != null ? convertToDTO(user) : null;
	}

	// Compare plain password and hashed password.
	public boolean checkPassword(String plainPassword, String hashedPassword) {
		System.out.println("Password Match: " + BCrypt.checkpw(plainPassword, hashedPassword));
		return BCrypt.checkpw(plainPassword, hashedPassword);
	}

	public UserDTO addUser(UserDTO userDTO) {
		try {
			User user = convertToEntity(userDTO);
			// Encrypt password before saving. Salted hashed.
			String hashedPassword = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(10));
			user.setPassword(hashedPassword);
			user.setRole("USER"); // 預設角色為 USER
			user.setAvatarUrl(DEFAULT_AVATAR_URL); // 給一張預設頭圖
			user.setBio("Write something about you! :)");

			User newUser = userRepository.save(user);
			return convertToDTO(newUser);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Email already in use.");
		}

	}

	public UserDTO updateUser(Long id, UserDTO userDTO) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		if (userDTO.getName() != null) {
			user.setName(userDTO.getName());
		}
		if (userDTO.getJobTitle() != null) {
			user.setJobTitle(userDTO.getJobTitle());
		}
		if (userDTO.getPassword() != null) {
			// Encrypt password before saving. Salted hashed.
			String hashedPassword = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(10));
			user.setPassword(hashedPassword);
		}
		// 不更新 role 也暫時不開放更新email
		User updatedUser = userRepository.save(user);
		return convertToDTO(updatedUser);
	}

	@Transactional
	public UserDTO updateAvatar(Long userId, String base64Avatar) throws IOException {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		// 設定 Base64 編碼的頭像 URL
		user.setAvatarUrl(base64Avatar);
		User updatedUser = userRepository.save(user);

		return convertToDTO(updatedUser);
	}

	@Transactional
	public UserDTO updateBio(Long userId, String bio) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		user.setBio(bio);
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

    public List<Integer> getUserFollowers(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<UserFollowing> followers = userFollowingRepository.findByFollowing(user);
        return followers.stream().map(f -> f.getUser().getId().intValue()).collect(Collectors.toList());
    }

    public List<Integer> getUserFollowing(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<UserFollowing> following = userFollowingRepository.findByUser(user);
        return following.stream().map(f -> f.getFollowing().getId().intValue()).collect(Collectors.toList());
    }

	// 追蹤用戶相關API

	@Transactional
	public void followUser(Long userId, Long artistId) {
		try {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            User artist = userRepository.findById(artistId).orElseThrow(() -> new RuntimeException("Artist not found"));
            UserFollowing userFollowing = new UserFollowing(user, artist);
            userFollowingRepository.save(userFollowing);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to update following status", e);
		}
	}

	@Transactional
	public void unfollowUser(Long userId, Long artistId) {
		try {
			Optional<UserFollowing> userFollowingOpt = userFollowingRepository.findByUserIdAndFollowingId(userId,
					artistId);
			if (userFollowingOpt.isPresent()) {
				userFollowingRepository.delete(userFollowingOpt.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to update following status", e);
		}
	}

	public boolean isFollowing(Long userId, Long followingId) {
		return userFollowingRepository.findByUserIdAndFollowingId(userId, followingId).isPresent();
	}

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setJobTitle(user.getJobTitle());
        userDTO.setBio(user.getBio());
        userDTO.setCreatedDate(user.getCreatedDate());
        userDTO.setUploadWorks(user.getUploadWorks());
        userDTO.setSavedWorks(user.getSavedWorks());
        userDTO.setLikedWorks(user.getLikedWorks());
        userDTO.setRole(user.getRole());
        userDTO.setAvatarUrl(user.getAvatarUrl());

        // 添加followers和following
        List<UserFollowing> followers = userFollowingRepository.findByFollowing(user);
        List<UserFollowing> following = userFollowingRepository.findByUser(user);
        
        userDTO.setFollowers(followers.stream().map(f -> f.getUser().getId().intValue()).collect(Collectors.toList()));
        userDTO.setFollowing(following.stream().map(f -> f.getFollowing().getId().intValue()).collect(Collectors.toList()));
        
        return userDTO;
    }

	private User convertToEntity(UserDTO userDTO) {
		User user = new User();
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setBio(userDTO.getBio());
		user.setPassword(userDTO.getPassword()); // 這裡先設置明文密碼，稍後進行salted hash
		return user;
	}

	private ArtistDTO convertToArtistDTO(User user) {
		ArtistDTO artistDTO = new ArtistDTO();
		artistDTO.setId(user.getId());
		artistDTO.setName(user.getName());
		artistDTO.setJobTitle(user.getJobTitle());
		artistDTO.setBio(user.getBio());
		artistDTO.setCreatedDate(user.getCreatedDate());
		artistDTO.setUploadWorks(user.getUploadWorks());
		artistDTO.setAvatarUrl(user.getAvatarUrl());

        // 添加followers和following
        List<UserFollowing> followers = userFollowingRepository.findByFollowing(user);
        List<UserFollowing> following = userFollowingRepository.findByUser(user);
        
        artistDTO.setFollowers(followers.stream().map(f -> f.getUser().getId().intValue()).collect(Collectors.toList()));
        artistDTO.setFollowing(following.stream().map(f -> f.getFollowing().getId().intValue()).collect(Collectors.toList()));
        
        return artistDTO;
	}
}