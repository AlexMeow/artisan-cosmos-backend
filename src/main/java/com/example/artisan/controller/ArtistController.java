package com.example.artisan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.artisan.model.dto.ArtistDTO;
import com.example.artisan.model.dto.UserDTO;
import com.example.artisan.model.po.User;
import com.example.artisan.service.UserService;
import com.example.artisan.util.JwtTokenUtil;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	// 取得所有用戶
	@GetMapping("/get-all-artists")
	public ResponseEntity<List<ArtistDTO>> findAllUsers() {
		List<ArtistDTO> artists = (List<ArtistDTO>)userService.findAllArtists();
		return ResponseEntity.ok(artists);
	}

	// 依用戶ID取得用戶
	@GetMapping("/{id}")
	public ResponseEntity<ArtistDTO> findUserById(@PathVariable Long id) {
		ArtistDTO artist = userService.findArtistById(id);
		return ResponseEntity.ok(artist);
	}

	/* ========= 以下待修改 ========= */
	
	// 取得用戶上傳的作品
	@GetMapping("/{id}/upload-works")
	public ResponseEntity<List<Integer>> getUserUploadWorks(@PathVariable Long id) {
		List<Integer> uploadWorks = userService.getUserUploadWorks(id);
		return ResponseEntity.ok(uploadWorks);
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