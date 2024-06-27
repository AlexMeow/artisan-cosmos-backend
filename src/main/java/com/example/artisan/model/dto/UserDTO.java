package com.example.artisan.model.dto;

import java.util.Date;
import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String bio;
    private Date createdDate;
    private List<Integer> uploadWorks;
    private List<Integer> savedWorks;
    private List<Integer> likedWorks;
    private List<Integer> followers;
    private List<Integer> following;

    // Constructors, getters and setters

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, String bio, Date createdDate,
                   List<Integer> uploadWorks, List<Integer> savedWorks, List<Integer> likedWorks,
                   List<Integer> followers, List<Integer> following) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.createdDate = createdDate;
        this.uploadWorks = uploadWorks;
        this.savedWorks = savedWorks;
        this.likedWorks = likedWorks;
        this.followers = followers;
        this.following = following;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

    public List<Integer> getSavedWorks() {
        return savedWorks;
    }

    public void setSavedWorks(List<Integer> savedWorks) {
        this.savedWorks = savedWorks;
    }

    public List<Integer> getLikedWorks() {
        return likedWorks;
    }

    public void setLikedWorks(List<Integer> likedWorks) {
        this.likedWorks = likedWorks;
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
}