package com.example.myapp.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class BoardCategory {
	private int categoryId;				// 카테고리 아이디
	private String categoryName;		// 카테고리 이름
	private String categoryDescription;	// 카테고리 설명
}