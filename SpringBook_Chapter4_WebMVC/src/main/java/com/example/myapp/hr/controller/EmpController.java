package com.example.myapp.hr.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myapp.hr.model.EmpVO;
import com.example.myapp.hr.service.IEmpService;

@Controller
public class EmpController {

	@Autowired
	IEmpService empService;
	
	@RequestMapping(value="/hr/count")
	public String empCount(@RequestParam(value="deptid", required=false, defaultValue="0") int deptid, Model model) {
		if(deptid==0) {
			model.addAttribute("count", empService.getEmpCount());
		}else {
			model.addAttribute("count", empService.getEmpCount(deptid));
		}
		return "hr/count";
	}

	@RequestMapping(value="/hr/list")
	public String getAllEmps(Model model) {
		List<EmpVO> empList = empService.getEmpList();
		model.addAttribute("empList", empList);
		return "hr/list";
	}

	@RequestMapping(value="/hr/{employeeId}")
	public String getEmpInfo(@PathVariable int employeeId, Model model) {
		EmpVO emp = empService.getEmpInfo(employeeId);
		model.addAttribute("emp", emp);
		return "hr/view";
	}

	@RequestMapping(value="/hr/insert", method=RequestMethod.GET)
	public String insertEmp(Model model) {
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("managerList", empService.getAllManagerId());
		return "hr/insertform";
	}
	
	@RequestMapping(value="/hr/insert", method=RequestMethod.POST)
	public String insertEmp(EmpVO emp, RedirectAttributes redirectAttributes) {
		try {
			empService.insertEmp(emp);
			redirectAttributes.addFlashAttribute("message", 
					emp.getEmployeeId()+"번 사원정보가 입력되었습니다.");
		}catch(RuntimeException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/hr/list";
	}
	
	@RequestMapping(value="/hr/update", method=RequestMethod.GET)
	public String updateEmp(int empid, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empid));
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("managerList", empService.getAllManagerId());
		return "hr/updateform";
	}
	
	@RequestMapping(value="/hr/update", method=RequestMethod.POST)
	public String updateEmp(EmpVO emp, RedirectAttributes redirectAttributes) {
		try {
			empService.updateEmp(emp);
			redirectAttributes.addFlashAttribute("message",
					emp.getEmployeeId() + "번 사원정보가 수정되었습니다.");
		}catch(RuntimeException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/hr/" + emp.getEmployeeId();
	}
	
	@RequestMapping(value="/hr/delete", method=RequestMethod.GET)
	public String deleteEmp(int empid, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empid));
		return "hr/deleteform";
	}

	@RequestMapping(value="/hr/delete", method=RequestMethod.POST)
	public String deleteEmp(int empid, String email, Model model, 
			RedirectAttributes redirectAttrs) {
		try {
			int deletedRow = empService.deleteEmp(empid, email);
			if(deletedRow>0) {
				redirectAttrs.addFlashAttribute("message",
						empid + "번 사원정보가 삭제되었습니다.");
				return "redirect:/hr/list";
			}else {
				model.addAttribute("message", "사번과 이메일 주소가 다릅니다.");
				model.addAttribute("emp", empService.getEmpInfo(empid));
				return "hr/deleteform";
			}					
		}catch(RuntimeException e) {
			redirectAttrs.addFlashAttribute("message", e.getMessage());
			return "redirect:/hr/list";
		}
	}
	
	@ExceptionHandler({SQLException.class, RuntimeException.class})
	public String runtimeException(HttpServletRequest request, Exception ex,
			Model model) {
		model.addAttribute("url", request.getRequestURI());
		model.addAttribute("exception", ex);
		return "error/runtime";
	}
	
	
	@RequestMapping(value="/hr/json")
	public @ResponseBody List<EmpVO> getEmpJSONList() {
		return empService.getEmpList();
	}
	
	@RequestMapping(value="/hr/json/{employeeId}")
	public @ResponseBody EmpVO getEmpJSONInfo(@PathVariable int employeeId) {
		return empService.getEmpInfo(employeeId);
	}
}//end class