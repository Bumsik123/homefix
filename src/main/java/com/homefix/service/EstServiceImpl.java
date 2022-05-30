package com.homefix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homefix.domain.Company;
import com.homefix.domain.Esti_request;
import com.homefix.domain.Estimation;
import com.homefix.domain.Member;
import com.homefix.persistence.CompanyRepository;
import com.homefix.persistence.ContractRepository;
import com.homefix.persistence.EstRepository;
import com.homefix.persistence.Esti_requestRepository;
import com.homefix.persistence.MemberRepository;

@Service
public class EstServiceImpl implements EstService {
	
	@Autowired
	EstRepository estRepo;
	
	@Autowired
	CompanyRepository companyRepo;
	
	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	ContractRepository contractRepo;
	
	@Autowired
	Esti_requestRepository esti_reqRepo; 

	@Override
	public Estimation getEstDetail(String id) {
		Estimation resultData = estRepo.getEstDetail(id);
		System.out.println("resultData는"+resultData);
		return resultData;
	}

	@Override
	public List<Estimation> getCEst(String cid) {
		Company com = companyRepo.findById(cid).get();
		return estRepo.findByCompany(com);
	}

	@Override
	public List<Estimation> getMEstimation(String id) {
		Member member = memberRepo.findById(id).get();
		return estRepo.findByMember(member);
	}

	//내(고객) 견적 리스트 상세보기
	@Override
	public Estimation getMEDetail(Integer id) {
		return estRepo.findById(id).get();
	}

	
}