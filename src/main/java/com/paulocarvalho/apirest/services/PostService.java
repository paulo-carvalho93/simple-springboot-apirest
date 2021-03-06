package com.paulocarvalho.apirest.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulocarvalho.apirest.domain.Post;
import com.paulocarvalho.apirest.repository.PostRepository;
import com.paulocarvalho.apirest.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}
	
	/*
	 * public List<Post> findByTitle(String text) { return
	 * postRepository.findByTitleContainingIgnoreCase(text); }
	 */
	
	public List<Post> findByTitle(String text) {
		return postRepository.searchByTitle(text);
	}
	
	public List<Post> searchFull(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24  * 60 * 60 * 1000);
		return postRepository.searchFull(text, minDate, maxDate);
	}
}
