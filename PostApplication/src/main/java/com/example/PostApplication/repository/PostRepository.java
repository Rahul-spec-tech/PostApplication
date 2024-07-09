package com.example.PostApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PostApplication.model.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	public Post getById(Long id);
}
