package com.example.artisan.model.dto;

import java.util.Date;
import java.util.List;

public class ArtistDTO {

	private Long id;
	private String name;
	private String bio;
	private Date createdDate;
	private List<Integer> uploadWorks;
//	private List<Integer> savedWorks;
//	private List<Integer> likedWorks;
	private List<Integer> followers;
	private List<Integer> following;
//	private String role;
	private String avatarUrl;

	// Constructors, getters and setters

	public ArtistDTO() {
	}

	public ArtistDTO(Long id, String name, String bio, Date createdDate, List<Integer> uploadWorks,
			List<Integer> followers, List<Integer> following, String avatarUrl) {
		this.id = id;
		this.name = name;
		this.bio = bio;
		this.createdDate = createdDate;
		this.uploadWorks = uploadWorks;
		this.followers = followers;
		this.following = following;
		this.avatarUrl = avatarUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<Integer> getUploadWorks() {
		return uploadWorks;
	}

	public void setUploadWorks(List<Integer> uploadWorks) {
		this.uploadWorks = uploadWorks;
	}


	public List<Integer> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Integer> followers) {
		this.followers = followers;
	}

	public List<Integer> getFollowing() {
		return following;
	}

	public void setFollowing(List<Integer> following) {
		this.following = following;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
}