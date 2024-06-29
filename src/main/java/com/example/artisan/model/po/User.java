package com.example.artisan.model.po;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    @Column
    private String jobTitle;

	@Column
    private String tags;

    @Column
    private String bio;

    @ElementCollection
    @CollectionTable(name = "user_upload_works", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "work_id")
    private List<Integer> uploadWorks;

    @ElementCollection
    @CollectionTable(name = "user_saved_works", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "work_id")
    private List<Integer> savedWorks;

    @ElementCollection
    @CollectionTable(name = "user_liked_works", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "work_id")
    private List<Integer> likedWorks;

    @ElementCollection
    @CollectionTable(name = "user_followers", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "follower_id")
    private List<Integer> followers;

    @ElementCollection
    @CollectionTable(name = "user_following", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "following_id")
    private List<Integer> following;
    
    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Date createdDate;
    
    @Column(nullable = false)
    private String role;
    
    @Lob
    @Column(name = "avatar_url", columnDefinition = "LONGTEXT")
    private String avatarUrl;
    
    // Getters and Setters

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
	
    public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
