package com.example.myapp.member;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.myapp.member.model.Member;

@Component
public class MemberValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Member.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Member member = (Member)target;
		
		String pw1 = member.getPassword();
		String pw2 = member.getPassword2();
		
		if(pw1 != null && pw1.equals(pw2)) {
			//pass
		}else {
			errors.rejectValue("password2", "PASSWORD_NOT_EQUALS", "비밀번호 확인이 다릅니다");
		}

	}

}
