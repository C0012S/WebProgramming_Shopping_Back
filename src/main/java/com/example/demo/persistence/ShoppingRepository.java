package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ShoppingEntity;

@Repository
public interface ShoppingRepository extends JpaRepository<ShoppingEntity, String> {
	List<ShoppingEntity> findByUserId(String userId);
	List<ShoppingEntity> findByTitle(String title);
}
