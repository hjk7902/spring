package com.example.myapp.upload.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.myapp.upload.model.UploadFileVO;

@Repository
public class UploadFileRepository implements IUploadFileRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int getMaxFileId() {
		String sql = "SELECT NVL(MAX(file_id),0) FROM upload_file";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	@Override
	public void uploadFile(UploadFileVO file) {
		String sql = "INSERT INTO upload_file "
					+ "	(file_id, directory_name, file_name, file_size, "
					+ " file_content_type, file_upload_date, file_data) "
					+ "	VALUES (?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";
		jdbcTemplate.update(sql,
				file.getFileId(),
				file.getDirectoryName(), 
				file.getFileName(), 
				file.getFileSize(), 
				file.getFileContentType(),
				file.getFileData());
	}

	@Override
	public List<UploadFileVO> getFileList(String directoryName) {
		String sql = "SELECT file_id, directory_name, file_name, file_size, "
					+ "	file_content_type, file_upload_date "
					+ " FROM upload_file WHERE directory_name=? "
					+ " ORDER BY file_upload_date DESC ";
		return jdbcTemplate.query(sql, new RowMapper<UploadFileVO>(){
			@Override
			public UploadFileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UploadFileVO file = new UploadFileVO();
				file.setFileId(rs.getInt("file_id"));
				file.setDirectoryName(rs.getString("directory_name"));
				file.setFileName(rs.getString("file_name"));
				file.setFileSize(rs.getLong("file_size"));
				file.setFileContentType(rs.getString("file_content_type"));
				file.setFileUploadDate(rs.getTimestamp("file_upload_date"));
				return file;
			}
		}, directoryName);
	}

	@Override
	public List<UploadFileVO> getAllFileList() {
	String sql = "SELECT file_id, directory_name, file_name, file_size, "
				+ "	file_content_type, file_upload_date " 
				+ " FROM upload_file "
				+ " ORDER BY file_upload_date DESC";
		return jdbcTemplate.query(sql, new RowMapper<UploadFileVO>(){
			@Override
			public UploadFileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UploadFileVO file = new UploadFileVO();
				file.setFileId(rs.getInt("file_id"));
				file.setDirectoryName(rs.getString("directory_name"));
				file.setFileName(rs.getString("file_name"));
				file.setFileSize(rs.getLong("file_size"));
				file.setFileContentType(rs.getString("file_content_type"));
				file.setFileUploadDate(rs.getTimestamp("file_upload_date"));
				return file;
			}
		});
	}
	
	@Override
	public List<UploadFileVO> getImageList(String directoryName) {
		String sql = "SELECT file_id, directory_name, file_name, file_size, "
					+ "	file_content_type, file_upload_date, file_data "
					+ " FROM upload_file "
					+ " WHERE directory_name=? "
					+ " ORDER BY file_upload_date DESC ";
		return jdbcTemplate.query(sql, new RowMapper<UploadFileVO>(){
			@Override
			public UploadFileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UploadFileVO file = new UploadFileVO();
				file.setFileId(rs.getInt("file_id"));
				file.setDirectoryName(rs.getString("directory_name"));
				file.setFileName(rs.getString("file_name"));
				file.setFileSize(rs.getLong("file_size"));
				file.setFileContentType(rs.getString("file_content_type"));
				file.setFileData(rs.getBytes("file_data"));
				file.setFileUploadDate(rs.getTimestamp("file_upload_date"));
				return file;
			}
		}, directoryName);
	}
	
	@Override
	public UploadFileVO getFile(int fileId) {
		String sql = "SELECT file_id, directory_name, file_name, file_size, "
					+ "	file_content_type, file_data "
					+ " FROM upload_file "
					+ " WHERE file_id=?";
		return jdbcTemplate.queryForObject(sql, new RowMapper<UploadFileVO>(){
			@Override
			public UploadFileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UploadFileVO file = new UploadFileVO();
				file.setFileId(rs.getInt("file_id"));
				file.setDirectoryName(rs.getString("directory_name"));
				file.setFileName(rs.getString("file_name"));
				file.setFileSize(rs.getLong("file_size"));
				file.setFileContentType(rs.getString("file_content_type"));
				file.setFileData(rs.getBytes("file_data"));
				return file;
			}
		}, fileId);
	}

	@Override
	public String getDirectoryName(int fileId) {
		String sql = "SELECT directory_name FROM upload_file WHERE file_id=?";
		return jdbcTemplate.queryForObject(sql, String.class, fileId);
	}

	@Override
	public void updateDirectory(HashMap<String, Object> map) {
		String sql = "UPDATE upload_file SET directory_name=? WHERE file_id=?";
		jdbcTemplate.update(sql, map.get("directoryName"), map.get("fileId"));
	}
	
	@Override
	public void deleteFile(int fileId) {
		String sql = "DELETE FROM upload_file WHERE file_id=?";
		jdbcTemplate.update(sql, fileId);
	}
	
//	@Override
//	public void updateFile(UploadFileVO file) {
//		String sql = "UPDATE upload_file "
//					+ " SET directory_name=?, "
//					+ " file_name=?, "
//					+ " file_size=?, "
//					+ " file_content_type=?, "
//					+ " file_data=?" 
//					+ " WHERE file_id=?";
//		jdbcTemplate.update(sql, 
//								file.getDirectoryName(), 
//								file.getFileName(), 
//								file.getFileSize(), 
//								file.getFileContentType(),
//								file.getFileData(),
//								file.getFileId());
//	} // 이 메서드는 수정을 위한 것이지만 실제 사용하지 않습니다.
}