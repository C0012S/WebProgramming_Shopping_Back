package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ShoppingEntity;
import com.example.demo.persistence.ShoppingRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShoppingService {

	@Autowired
	private ShoppingRepository repository;
	
	public List<ShoppingEntity> create(final ShoppingEntity entity) {
	
		validate(entity);
		
		repository.save(entity);
		
		return repository.findByUserId(entity.getUserId());
	}
	
/*	// 텀 프로젝트 1 - retrieve (제목 검색)
	public List<ShoppingEntity> retrieve(final String title) {
		return repository.findByTitle(title);
	}
*/	
	// 텀 프로젝트 2 - retrieve & search
	public List<ShoppingEntity> retrieve(final String userId) {
		return repository.findByUserId(userId);
	}
	public List<ShoppingEntity> search(final String title) {
		return repository.findByTitle(title);
	}

/*
  	// 텀 프로젝트 1 - update
	public List<ShoppingEntity> update(final ShoppingEntity entity) {
		validate(entity);
		Optional<ShoppingEntity> original = repository.findById(entity.getId());
		original.ifPresent(shopping -> {
			shopping.setTitle(entity.getTitle());
			shopping.setCategory(entity.getCategory());
			shopping.setMaker(entity.getMaker());
			repository.save(shopping);
		});
		
		return retrieve(entity.getTitle());
	}
*/	
	// 텀 프로젝트 2 - update
	public List<ShoppingEntity> update(final ShoppingEntity entity) {
		validate(entity);
		Optional<ShoppingEntity> original = repository.findById(entity.getId());
		original.ifPresent(shopping -> {
			shopping.setTitle(entity.getTitle());
			shopping.setCategory(entity.getCategory());
			shopping.setMaker(entity.getMaker());
			repository.save(shopping);
		});
		
		return retrieve(entity.getUserId());
	}

	public List<ShoppingEntity> delete(final ShoppingEntity entity) {
		validate(entity);

		try {
			repository.delete(entity);
		} catch(Exception e) {
			log.error("error deleting entity ", entity.getId(), e);
			throw new RuntimeException("error deleting entity" + entity.getId());
		}
		
		return repository.findByUserId(entity.getUserId());
	}
	
	
	//리팩토링
	private void validate(final ShoppingEntity entity) {
		if(entity == null) {
			throw new RuntimeException("Entity cannot be null");
		}
		
		if(entity.getUserId() == null) {
			throw new RuntimeException("Unknown user.");
		}
	}
}
