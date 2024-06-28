package com.example.artisan.model.dto;

import java.util.List;

public class WorkDTO {
	private Long id;
	private String name;
	private List<String> tags;
	private String description;
	private List<String> imgUrls;
	private Long authorId;
	private ArtistDTO author;  // 包含作者的信息

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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(List<String> imgUrls) {
		this.imgUrls = imgUrls;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public ArtistDTO getArtist() {
		return author;
	}

	public void setArtist(ArtistDTO author) {
		this.author = author;
	}
}
