package com.example.PostApp.DTO;


import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class PostDTO {
	@NotBlank(message="Title cannot be empty")
	private String title;
	
	@NotBlank(message="Content cannot be empty")
	private String content;
	
	private LocalDateTime createdAt;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}