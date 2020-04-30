package com.paulocarvalho.apirest.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.paulocarvalho.apirest.domain.Post;
import com.paulocarvalho.apirest.domain.User;
import com.paulocarvalho.apirest.dto.AuthorDTO;
import com.paulocarvalho.apirest.dto.CommentDTO;
import com.paulocarvalho.apirest.repository.PostRepository;
import com.paulocarvalho.apirest.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("30/04/2020"), "Lindo dia!", "Gratidão!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("06/06/2020"), "Bom dia!", "Partiu!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem!", sdf.parse("21/08/2020"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Enjoy!!", sdf.parse("11/06/2020"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um bom dia!!", sdf.parse("16/06/2020"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
