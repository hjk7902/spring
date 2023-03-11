package com.example.myapp.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.myapp.dao.ISampleRepository;
import com.example.myapp.model.SampleVO;
@Service
public class SampleService implements ISampleService {
    @Autowired
    ISampleRepository boardRepository;

    @Override
    public List<SampleVO> selectSampleData() {
        return boardRepository.selectSampleData();
    }
}
