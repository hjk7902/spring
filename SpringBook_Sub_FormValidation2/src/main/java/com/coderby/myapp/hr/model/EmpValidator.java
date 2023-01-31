package com.coderby.myapp.hr.model;

import java.sql.Date;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmpValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EmpVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		EmpVO emp =(EmpVO)target;
		
		int employeeId = emp.getEmployeeId();
		if(employeeId < 300) {
			errors.rejectValue("employeeId", "emp.employeeId.min.300", "[사원번호는 300이상여야 합니다.]");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", 
				"emp.firstName.empty", "[사원이름은 반드시 입력되어야 합니다.]");
		
		String namePattern = "[a-zA-Z가-힣]{2,}";
		
		String firstName = emp.getFirstName();
		if(!Pattern.matches(namePattern, firstName)) {
			errors.rejectValue("firstName", "emp.firstName.pattern", "[사원이름은 2자 이상 입력되어야 합니다.]");
		}
		
		String emailPattern = "[A-Z0-9]{2,}";
		String email = emp.getEmail();
		if(!Pattern.matches(emailPattern, email)) {
			errors.rejectValue("email", "emp.email.pattern", "[이메일은 영문 대문자와 숫자로 2자 이상 입력되어야 합니다.]");
		}
		
		String phonePattern = "^[0-9]{2,3}[-\\.]?[0-9]{3,4}[-\\.]?[0-9]{4}$";
		String phoneNumber = emp.getPhoneNumber();
		if(!Pattern.matches(phonePattern, phoneNumber)) {
			errors.rejectValue("phoneNumber", "emp.phoneNumber.pattern", "[유효한 전화번호가 아닙니다.]");
		}
		
		Date hireDate = emp.getHireDate();
		Date today = new Date(System.currentTimeMillis());
		if(hireDate.after(today)) {
			errors.rejectValue("hireDate", "emp.hireDate.after", "[입사일은 미래의 날짜가 지정될 수 없습니다.]");
		}
		
		double salary = emp.getSalary();
		if(salary<100) {
			errors.rejectValue("salary", "emp.salary.min.100", "[급여는 100보다 작을 수 없습니다.]");
		}
		
		double commissionPct = emp.getCommissionPct();
		if(commissionPct<0 || commissionPct>=1) {
			errors.rejectValue("commissionPct", "emp.commissionPct.zero2one", "[보너스율은 0이상 1미만여야 합니다.]");
		}
	}

}
