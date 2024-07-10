package com.example.PostApplication.controller;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PostApplication.model.Post;
import com.example.PostApplication.repository.PostRepository;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostRepository postRepo;
	
	@PostMapping("/addBlog")
	public ResponseEntity<Post> addBlogPost(@RequestBody Post post){
		post.setCreatedAt(LocalDateTime.now());
		Post blogData=postRepo.save(post);
		return new ResponseEntity<>(blogData,HttpStatus.OK);
	}
	
	@GetMapping("/getAllPosts")
	public ResponseEntity<List<Post>> getAllPosts(){
		try {
			List<Post> postDetails=new ArrayList<>();
			
			postRepo.findAll().forEach(postDetails::add);
			if(postDetails.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(postDetails,HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getPostById/{Id}")
	public ResponseEntity<Post> getPostById(@PathVariable("Id") Long id){
		Optional<Post> getPost=postRepo.findById(id);
		if(getPost.isPresent()) {
			return new ResponseEntity<>(getPost.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/updatePostById/{Id}")
	public ResponseEntity<Post> updatePost(@PathVariable("Id") Long id,@RequestBody Post post) {
		Optional<Post> oldPost=postRepo.findById(id);
		if(oldPost.isPresent()) {
			Post updatedPostData=oldPost.get();
			updatedPostData.setTitle(post.getTitle());
			updatedPostData.setContent(post.getContent());
			Post updatedPost=postRepo.save(updatedPostData);
			return new ResponseEntity<>(updatedPost,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deletePostById/{Id}")
	public ResponseEntity<HttpStatus> deletePostById(@PathVariable("Id") Long id){
		postRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
