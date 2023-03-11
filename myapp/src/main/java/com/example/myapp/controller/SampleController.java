package com.example.myapp.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.myapp.model.SampleVO;
import com.example.myapp.service.ISampleService;
@Controller
public class SampleController {
    @Autowired
    ISampleService sampleService;

    @RequestMapping("/sample/list")
    public String getListData(Model model) {
        List<SampleVO> sampleList = sampleService.selectSampleData();
        model.addAttribute("sampleList", sampleList);
        System.out.println("Sample List");
        return "list";
    }
}
