package com.example.artisan.model.po;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
      name = "work_tags", 
      joinColumns = @JoinColumn(name = "work_id"), 
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @Column
    private String description;

    @Column
    private int likes;

    @Column(name = "saved_count")
    private int savedCount;

    @Lob
    @ElementCollection
    @CollectionTable(name = "work_img_urls", joinColumns = @JoinColumn(name = "work_id"))
    @Column(name = "img_url", columnDefinition = "LONGTEXT")
    private List<String> imgUrls;

//    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    
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


	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getSavedCount() {
		return savedCount;
	}

	public void setSavedCount(int savedCount) {
		this.savedCount = savedCount;
	}

	public List<String> getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(List<String> imgUrls) {
		this.imgUrls = imgUrls;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
    
}