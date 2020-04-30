package com.paulocarvalho.apirest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.paulocarvalho.apirest.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

}