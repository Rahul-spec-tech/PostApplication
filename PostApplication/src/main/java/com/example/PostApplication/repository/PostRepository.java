package com.example.PostApplication.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PostApplication.model.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, UUID>{
	public Post getById(UUID id);
	//public Post getByTitle(String title);
	List<Post> findByTitle(String title);
}
