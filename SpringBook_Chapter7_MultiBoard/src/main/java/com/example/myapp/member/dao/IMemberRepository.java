package com.example.myapp.member.dao;

import java.util.List;

import com.example.myapp.member.model.Member;

public interface IMemberRepository {
	void insertMember(Member member) ;
	Member selectMember(String userid);
	List<Member> selectAllMembers();
	void updateMember(Member member);
	void deleteMember(Member member);
	String getPassword(String userid);
}