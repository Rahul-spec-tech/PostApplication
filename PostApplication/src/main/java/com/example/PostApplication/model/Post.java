package com.example.PostApplication.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String Title;
	private String Content;
	@Column(name="Created_At",updatable=false)
	private LocalDateTime CreatedAt;
	@Column(name="Update_At")
	private LocalDateTime UpdatedAt;
	@PrePersist
	protected void onCreate() {
		CreatedAt=LocalDateTime.now();
	}
	@PreUpdate
	protected void onUpdate() {
		UpdatedAt=LocalDateTime.now();
	}
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Post(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.Id = id;
		this.Title = title;
		this.Content = content;
		this.CreatedAt = createdAt;
		this.UpdatedAt = updatedAt;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		this.Id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		this.Title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		this.Content = content;
	}
	public LocalDateTime getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.CreatedAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return UpdatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.UpdatedAt = updatedAt;
	}
}
