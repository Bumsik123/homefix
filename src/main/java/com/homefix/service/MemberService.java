package com.homefix.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.homefix.domain.Company;

public interface MemberService {
	
	// 사업자 아이디 중복 조회
	/* public Company idCheck(String id); */
	public String  idCheck(String id); 
	
	// 사업자 이메일 중복 조회
	public String emailCheck(String email);
	
	//로그인 성공
	public List<Company> login(Company com);
	
	// 사업자 회원 탈퇴
	public void companyDelete(String id);
	
	// 사업자 정보수정
	public void companyUpdate(Company com);
	
	// 사업자 회원가입
	public void companyInsert(Company com);
	
	//비밀번호 찾기
	public void findIdPwPOST(HttpServletResponse response, Company com);
	
}