package com.example.myapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.myapp.model.SampleVO;

// import …생략…;
@Repository
public class SampleRepository implements ISampleRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<SampleVO> selectSampleData() {
        String sql = "select id, name, email from sample";
        return jdbcTemplate.query(sql, new RowMapper<SampleVO>() {
            @Override
            public SampleVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                SampleVO vo = new SampleVO();
                vo.setId(rs.getInt("id"));
                vo.setName(rs.getString("name"));
                vo.setEmail(rs.getString("email"));
                return vo;
            }
       });
    }
}
