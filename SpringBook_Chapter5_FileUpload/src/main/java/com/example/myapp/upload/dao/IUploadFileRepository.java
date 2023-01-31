package com.example.myapp.upload.dao;

import java.util.HashMap;
import java.util.List;

import com.example.myapp.upload.model.UploadFileVO;


public interface IUploadFileRepository {
	int getMaxFileId();
	void uploadFile(UploadFileVO file);

	List<UploadFileVO> getFileList(String directoryName);
	List<UploadFileVO> getAllFileList();
	List<UploadFileVO> getImageList(String directoryName);

	UploadFileVO getFile(int fileId);

	String getDirectoryName(int fileId);
	void updateDirectory(HashMap<String, Object> map);

	void deleteFile(int fileId);
}
