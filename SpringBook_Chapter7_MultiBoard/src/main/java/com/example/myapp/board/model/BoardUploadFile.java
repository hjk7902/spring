package com.example.myapp.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString(exclude="fileData")
public class BoardUploadFile {
	private int fileId;		// 파일 아이디, 1씩 증가
	private int boardId;		// 첨부파일이 있는 게시글의 아이디(글번호)
	private String fileName;	// 파일 이름
	private long fileSize;		// 파일 크기
	private String fileContentType;	// 파일 타입(MIME Type)
	private byte[] fileData;		// 파일 데이터
}