package com.example.PostApplication.controller;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
import com.example.PostApplication.DTO.PostDTO;
import com.example.PostApplication.model.Post;
import com.example.PostApplication.repository.PostRepository;
@RestController
@RequestMapping("/PostApp")
public class PostController {
	
	@Autowired
	private PostRepository postRepo;
	
	//Add new Post
	@PostMapping("/Add_Blog")
	public ResponseEntity<Post> addBlogPost(@RequestBody PostDTO postDTO){
		if (postDTO.getTitle() == null || postDTO.getTitle().trim().isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
		Post post = new Post();
	    post.setTitle(postDTO.getTitle());
	    post.setContent(postDTO.getContent());
		post.setCreatedAt(LocalDateTime.now());
		Post blogData=postRepo.save(post);
		return new ResponseEntity<>(blogData,HttpStatus.OK);
	}
	
	//Get all Post
	@GetMapping("/Get_All_Posts")
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
	
	@GetMapping("/Get_Post_By_Id/{Id}")
	public ResponseEntity<Post> getPostById(@PathVariable("Id") UUID id) {
	    Optional<Post> getPost = postRepo.findById(id);
	    if (getPost.isPresent()) {
	        return new ResponseEntity<>(getPost.get(), HttpStatus.OK);
	    }
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//Get Post By Title
	@GetMapping("/Get_Post_By_Title/{Title}")
	public ResponseEntity<List<Post>> getPostByTitle(@PathVariable("Title") String title) {
	    try {
	        List<Post> postDetails = postRepo.findByTitle(title);
	        if (postDetails.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<>(postDetails, HttpStatus.OK);
	    } catch (Exception ex) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	//Update Post By Id
	@PostMapping("/Update_Post_By_Id/{Id}")
	public ResponseEntity<Post> updatePost(@PathVariable("Id") UUID id,@RequestBody Post post) {
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
	
	//Delete Post By Id
	@DeleteMapping("/Delete_Post_By_Id/{Id}")
	public ResponseEntity<HttpStatus> deletePostById(@PathVariable("Id") UUID id){
		postRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
