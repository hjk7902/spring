package com.example.myapp.member.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.myapp.member.service.MemberService;

@Component
public class MemberUserDetailsService implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MemberService memberService;

	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		Member memberInfo = memberService.selectMember(memberId);
		if (memberInfo==null) {
			throw new UsernameNotFoundException("["+ memberId +"] 사용자를 찾을 수 없습니다.");
		}
		memberInfo.setRoles(new String[]{"ROLE_USER", "ROLE_ADMIN"});
		logger.info(memberInfo.getUserid());

		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(memberInfo.getRoles());

//		return new User(memberInfo.getUserid(), "{noop}"+memberInfo.getPassword(), authorities);
		return new MemberUserDetails(memberInfo.getUserid(), 
				memberInfo.getPassword(), authorities, 
				memberInfo.getEmail());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}

