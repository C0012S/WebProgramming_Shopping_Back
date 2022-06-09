package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.ShoppingDTO;
import com.example.demo.model.ShoppingEntity;
import com.example.demo.service.ShoppingService;

@RestController
@RequestMapping("shopping")
public class ShoppingController {
	
	@Autowired
	private ShoppingService service;
	
	@PostMapping
	public ResponseEntity<?> createShopping(@RequestBody ShoppingDTO dto) {
		try {
//			String temporaryUserId = "Sang Hee Park";
//			String temporaryUserId = dto.getUserId();
			
			ShoppingEntity entity = ShoppingDTO.toEntity(dto);
			entity.setId(null);
//			entity.setUserId(temporaryUserId);
			List<ShoppingEntity> entities = service.create(entity);
			List<ShoppingDTO> dtos = entities.stream().map(ShoppingDTO::new).collect(Collectors.toList());
			ResponseDTO<ShoppingDTO> response = ResponseDTO.<ShoppingDTO>builder().data(dtos).build();

			return ResponseEntity.ok().body(response);			
		} catch(Exception e) {
			String error = e.getMessage();
			ResponseDTO<ShoppingDTO> response = ResponseDTO.<ShoppingDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> retrieveShoppingList(@RequestBody ShoppingDTO dto) {
		String title = dto.getTitle();

		List<ShoppingEntity> entities = service.retrieve(title);
		List<ShoppingDTO> dtos = entities.stream().map((e)->(new ShoppingDTO(e))).collect(Collectors.toList());
		ResponseDTO<ShoppingDTO> response = ResponseDTO.<ShoppingDTO>builder().data(dtos).build();

		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping
	public ResponseEntity<?> updateTodo(@RequestBody ShoppingDTO dto) {
//		String temporaryUserId = "Sang Hee Park";
//		String temporaryUserId = dto.getUserId();
		
		ShoppingEntity entity = ShoppingDTO.toEntity(dto);
//		entity.setUserId(temporaryUserId);
		List<ShoppingEntity> entities = service.update(entity);
		List<ShoppingDTO> dtos = entities.stream().map((e)->(new ShoppingDTO(e))).collect(Collectors.toList());
		ResponseDTO<ShoppingDTO> response = ResponseDTO.<ShoppingDTO>builder().data(dtos).build();

		return ResponseEntity.ok().body(response);		
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteTodo(@RequestBody ShoppingDTO dto) {
		try {
//			String temporaryUserId = "Sang Hee Park";
//			String temporaryUserId = dto.getUserId();
			
			ShoppingEntity entity = ShoppingDTO.toEntity(dto);
//			entity.setUserId(temporaryUserId);
			List<ShoppingEntity> entities = service.delete(entity);
			List<ShoppingDTO> dtos = entities.stream().map((e)->(new ShoppingDTO(e))).collect(Collectors.toList());
			ResponseDTO<ShoppingDTO> response = ResponseDTO.<ShoppingDTO>builder().data(dtos).build();

			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<ShoppingDTO> response = ResponseDTO.<ShoppingDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
}