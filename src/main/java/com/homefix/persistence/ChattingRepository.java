package com.homefix.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.homefix.domain.Chatting;
import com.homefix.domain.Company;
import com.homefix.domain.Member;


public interface ChattingRepository extends CrudRepository<Chatting, Integer>{
	Chatting findByMemberAndCompany(Member member,Company company);
	
	public List<Chatting> findByCompany(Company company);
	
	public List<Chatting> findByMember(Member member);
}
