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

	// �� ������Ʈ 1 & �� ������Ʈ 2 & �� ������Ʈ 3
	@Autowired
	private ShoppingRepository repository;
	
	public List<ShoppingEntity> create(final ShoppingEntity entity) {
	
		validate(entity);
		
		repository.save(entity);
		
		return repository.findByUserId(entity.getUserId());
	}
	
/*	// �� ������Ʈ 1 - retrieve (���� �˻�)
	public List<ShoppingEntity> retrieve(final String title) {
		return repository.findByTitle(title);
	}
*/	
	// �� ������Ʈ 2 & �� ������Ʈ 3 - retrieve
	public List<ShoppingEntity> retrieve(final String userId) {
		return repository.findByUserId(userId);
	}
	// �� ������Ʈ 2 & �� ������Ʈ 3 - search
	public List<ShoppingEntity> search(final String title) {
		return repository.findByTitle(title);
	}

/*
  	// �� ������Ʈ 1 - update
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
	// �� ������Ʈ 2 & �� ������Ʈ 3 - update
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

	// �� ������Ʈ 1 & �� ������Ʈ 2 & �� ������Ʈ 3
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
	
	
	//�����丵
	private void validate(final ShoppingEntity entity) {
		if(entity == null) {
			throw new RuntimeException("Entity cannot be null");
		}
		
		if(entity.getUserId() == null) {
			throw new RuntimeException("Unknown user.");
		}
	}
}
