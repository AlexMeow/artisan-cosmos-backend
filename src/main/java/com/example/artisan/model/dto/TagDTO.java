package com.example.artisan.model.dto;

import java.util.Set;

import com.example.artisan.model.po.User;
import com.example.artisan.model.po.Work;

public class TagDTO {

	private Long id;
	private String name;
	private Set<Work> works;
//	private Set<User> users;

	// Constructors

	public TagDTO(Long id, String name, Set<Work> works) {
		this.id = id;
		this.name = name;
		this.works = works;
//		this.users = users;
	}

	public TagDTO(Long id, String name) {
		this.id = id;
		this.name = name;
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

	public Set<Work> getWorks() {
		return works;
	}

	public void setWorks(Set<Work> works) {
		this.works = works;
	}

//	public Set<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}

}
