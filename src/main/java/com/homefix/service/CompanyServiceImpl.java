package com.homefix.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.homefix.domain.Company;
import com.homefix.domain.CompanyDto;
import com.homefix.persistence.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepo;

	// 사업자 아이디 중복 조회
	public String idCheck(String id) {
		String message= "Y";
		 if(companyRepo.countById(id)>0) {
			 message= "N";
		 } 
		 return message;
	} 

	// 사업자 이메일 중복 조회
	public String emailCheck(String email) {
		String message = "Y";
		if (companyRepo.findByEmail(email).size() > 0) {
			message= "N";
		}
		return message;
	}

	// 사업자번호 중복 조회
	public String companyNumberCheck(String num) {
		String message= "Y";
		if (companyRepo.countByNum(num) > 0) {
			message= "N";
		}
		return message;
	}
 
	// 로그인 성공
	public String login(Company com) {
		List<Company> list = companyRepo.findByIdAndPass(com.getId(), com.getPass());
		String message = null;
		if (list.size() >0) {
			message = list.get(0).getName();
		}
		return message;
	} 

	// 사업자 회원 탈퇴
	public void companyDelete(String id) {
		companyRepo.deleteById(id);
	}
  
	// 사업자 정보수정
	public void companyUpdate(CompanyDto dto) {
		companyRepo.save(dto); 
	}

	// 사업자 회원가입
	public void companyInsert(Company com) {
		 companyRepo.save(com); 
	}

	
	/* 회원가입 시, 유효성 체크 */
	@Transactional(readOnly = true)
	public Map<String, String> validateHandling(Errors errors) {
	Map<String, String> validatorResult = new HashMap<>();
	/* 유효성 검사에 실패한 필드 목록을 받음 */
	for (FieldError error : errors.getFieldErrors()) {
	String validKeyName = String.format("valid_%s", error.getField());
	validatorResult.put(validKeyName, error.getDefaultMessage());
	}
	return validatorResult;
	}

	
	
}