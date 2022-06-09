package com.example.demo.dto;

import com.example.demo.model.ShoppingEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoppingDTO {
	// 판매할 제품 : 액세서리
	private String id; // 제품 id
	private String userId; // 사용자 아이디
	private String title; // 제품 이름
	private String category; // 제품 종류
	private String maker; // 제품 브랜드
	
	public ShoppingDTO(final ShoppingEntity entity) {
		this.id = entity.getId();
		this.userId = entity.getUserId();
		this.title = entity.getTitle();
		this.category = entity.getCategory();
		this.maker = entity.getMaker();
	}
	
	public static ShoppingEntity toEntity(final ShoppingDTO dto) {
		return ShoppingEntity.builder()
				.id(dto.getId())
				.userId(dto.getUserId())
				.title(dto.getTitle())
				.category(dto.getCategory())
				.maker(dto.getMaker())
				.build();
	}
}
