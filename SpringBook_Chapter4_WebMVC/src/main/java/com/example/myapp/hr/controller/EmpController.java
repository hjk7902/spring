package com.example.myapp.hr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myapp.hr.EmpNotFoundException;
import com.example.myapp.hr.model.Emp;
import com.example.myapp.hr.service.IEmpService;

@Controller
public class EmpController {

	@Autowired
	IEmpService empService;

	@GetMapping({"/hr/count", "/hr/cnt"})
	public String empCount(@RequestParam(value="deptid", required=false, defaultValue="0") int deptid, Model model) {
		if(deptid==0) {
			model.addAttribute("count", empService.getEmpCount()); // 모든 사원의 수
		}else {
			model.addAttribute("count", empService.getEmpCount(deptid)); // 부서 사원의 수
		}
		return "hr/count"; // WEB-INF/views/hr/count.jsp
	}

	@GetMapping(value="/hr/list")
	public String getAllEmps(Model model) {
		List<Emp> empList = empService.getEmpList();
		model.addAttribute("empList", empList);
		return "hr/list";
	}

	@GetMapping("/hr/{employeeId:[0-9]+}")
	public String getEmpInfo(@PathVariable int employeeId, Model model) {
		try {
			Emp emp = empService.getEmpInfo(employeeId);
			model.addAttribute("emp", emp);
		}catch(EmptyResultDataAccessException e) {
			throw new EmpNotFoundException("사원이 없습니다.");
		}
		return "hr/view";
	}

	@GetMapping("/hr/insert")
	public String insertEmp(Model model) {
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("managerList", empService.getAllManagerId());
		return "hr/insertform";
	}

	@PostMapping(value="/hr/insert")
	public String insertEmp(Emp emp, RedirectAttributes redirectAttrs) {
		try {
			empService.insertEmp(emp);
			redirectAttrs.addFlashAttribute("message", 
					emp.getEmployeeId() + "번 사원정보가 입력되었습니다.");
		}catch(RuntimeException e) {
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/hr/list";
	}

	@GetMapping(value="/hr/update")
	public String updateEmp(int empid, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empid));
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("managerList", empService.getAllManagerId());
		return "hr/updateform";
	}
	
	@PostMapping(value="/hr/update")
	public String updateEmp(Emp emp, RedirectAttributes redirectAttributes) {
		try {
			empService.updateEmp(emp);
			redirectAttributes.addFlashAttribute("message",
					emp.getEmployeeId() + "번 사원정보가 수정되었습니다.");
		}catch(RuntimeException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/hr/" + emp.getEmployeeId();
	}

	@GetMapping(value="/hr/delete")
	public String deleteEmp(int empid, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empid));
		return "hr/deleteform";
	}
 
	@PostMapping(value="/hr/delete")
	public String deleteEmp(int empid, String email, RedirectAttributes redAttr, Model model) {
		try {
			int deletedRow = empService.deleteEmp(empid, email);
			if(deletedRow > 0) {
				redAttr.addFlashAttribute("message", empid + "번 사원정보가 삭제되었습니다.");
				return "redirect:/hr/list";
			}else {
				model.addAttribute("message", "ID 또는 Email이 다릅니다.");
				model.addAttribute("emp", empService.getEmpInfo(empid));
				return "hr/deleteform";
			}					
		}catch(RuntimeException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("emp", empService.getEmpInfo(empid));
			return "hr/deleteform";
		}
	}
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler({RuntimeException.class, EmptyResultDataAccessException.class})
	public String runtimeException(HttpServletRequest request, Exception ex, Model model) {
		logger.error("Emp Error: URL-{}, EX-{} ", request.getRequestURL(), ex);
		model.addAttribute("exception", ex);
		model.addAttribute("url", request.getRequestURL());
		return "error/runtime";
	}
	
	@ResponseBody 
	@GetMapping(value="/hr/json")
	public List<Emp> getEmpJSONList() {
		List<Emp> empList = empService.getEmpList();
		return empList;
	}
	
	@GetMapping(value="/hr/json/{employeeId}")
	public @ResponseBody Emp getEmpJSONInfo(@PathVariable int employeeId) {
		Emp emp = empService.getEmpInfo(employeeId);
		return emp;
	}
	

}

