package com.example.myapp.upload.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString(exclude="fileData")
public class UploadFileVO {
	private int fileId;
	private String directoryName;
	private String fileName;
	private long fileSize;
	private String fileContentType;
	private Timestamp fileUploadDate;
	private byte[] fileData;
}