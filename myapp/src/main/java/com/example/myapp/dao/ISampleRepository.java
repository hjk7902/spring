package com.example.myapp.dao;
import java.util.List;
import com.example.myapp.model.SampleVO;
public interface ISampleRepository {
	List<SampleVO> selectSampleData();
}
