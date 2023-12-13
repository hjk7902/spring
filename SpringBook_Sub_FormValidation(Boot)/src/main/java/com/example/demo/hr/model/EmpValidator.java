package com.example.demo.hr.model;

import java.sql.Date;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@Component
public class EmpValidator implements Validator, InitializingBean {

	private jakarta.validation.Validator validator;

	@Override
	public void afterPropertiesSet() throws Exception {
		ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();
		validator = vFactory.usingContext().getValidator();
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Emp.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Set<ConstraintViolation<Object>> violations = validator.validate(target);
		for (ConstraintViolation<Object> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			errors.rejectValue(propertyPath, message, message);
		}
		
		Emp emp =(Emp)target;
		
		int employeeId = emp.getEmployeeId();
		if(employeeId < 300) {
			errors.rejectValue("employeeId", "Min.emp.employeeId", "[사원번호는 300이상여야 합니다.]");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotNull.emp.firstName", "[사원이름은 반드시 입력되어야 합니다.]");
		
		String firstNamePattern = "[a-zA-Z가-힣]{2,}";
		String firstName = emp.getFirstName();
		if(!Pattern.matches(firstNamePattern, firstName)) {
			errors.rejectValue("firstName", "Pattern.emp.firstName", "[이름은 2자 이상 입력되어야 합니다.]");
		}
		
		String lastNamePattern = "[a-zA-Z가-힣]{1,}";
		String lastName = emp.getLastName();
		if(!Pattern.matches(lastNamePattern, lastName)) {
			errors.rejectValue("lastName", "Pattern.emp.lastName", "[성은 1자 이상 입력되어야 합니다.]");
		}
		
		String emailPattern = "[A-Z0-9]{2,}";
		String email = emp.getEmail();
		if(!Pattern.matches(emailPattern, email)) {
			errors.rejectValue("email", "Pattern.emp.email", "[이메일은 영문 대문자와 숫자로 2자 이상 입력되어야 합니다.]");
		}
		
		String phonePattern = "^[0-9]{2,3}[-\\.]?[0-9]{3,4}[-\\.]?[0-9]{4}$";
		String phoneNumber = emp.getPhoneNumber();
		if(!Pattern.matches(phonePattern, phoneNumber)) {
			errors.rejectValue("phoneNumber", "Pattern.emp.phoneNumber", "[유효한 전화번호가 아닙니다.]");
		}
		
		Date hireDate = emp.getHireDate();
		Date today = new Date(System.currentTimeMillis());
		if(hireDate == null) {
			errors.rejectValue("hireDate", "NotNull.emp.hireDate", "[입사일을 선택하세요.]");
		}else if(hireDate.after(today)) {
			errors.rejectValue("hireDate", "Past.emp.hireDate", "[입사일은 미래의 날짜가 지정될 수 없습니다.]");
		}
		
		double salary = emp.getSalary();
		if(salary<100) {
			errors.rejectValue("salary", "Min.emp.salary", "[급여는 100보다 작을 수 없습니다.]");
		}
		
		double commissionPct = emp.getCommissionPct();
		if(commissionPct<0) {
			errors.rejectValue("commissionPct", "DecimalMin.emp.commissionPct", "[보너스율은 0보다 작을 수 없습니다.]");
		}
		if(commissionPct>0.99) {
			errors.rejectValue("commissionPct", "DecimalMax.emp.commissionPct", "[보너스율은 0.99보다 클 수 없습니다.]");
		}
	}
}