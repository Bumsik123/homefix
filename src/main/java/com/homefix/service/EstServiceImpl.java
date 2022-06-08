package com.homefix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.homefix.domain.Chatting;
import com.homefix.domain.Company;
import com.homefix.domain.Contract;
import com.homefix.domain.Esti_request;
import com.homefix.domain.Estimation;
import com.homefix.domain.Member;
import com.homefix.persistence.ChattingRepository;
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
	public List<Estimation> getCEst(String cid, Integer page) {
		Company com = companyRepo.findById(cid).get();
		
		int showCntPerPage = 6; 
		  
		  Pageable pageable = (Pageable) PageRequest.of(page - 1, showCntPerPage,
		  Sort.by("startDay"));
		 
		
		return estRepo.findByCompany(com, pageable);
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

	@Override
	public List<Esti_request> getMEDetailC(Integer id) {
		Estimation estimation = estRepo.findById(id).get();
		return esti_reqRepo.findByEstimation(estimation);
	}

	//업체가 진행중인 견적 리스트 보기
	@Override 
	public List<Contract> getCIngList(String cid) {
		Company company  = companyRepo.findById(cid).get();
		return contractRepo.findByCompany(company);
	}

	@Override
	public void saveIng(Integer eid) {
		Estimation estimation = estRepo.findById(eid).get();
	 	Contract contract = contractRepo.findByEstimation(estimation);
		contract.setIng("시공완료"); 
		contractRepo.save(contract);
	}

	//업체에게 온 견적 상세보기에서 확정하기 클릭 시  esti_request테이블에 값 저장
	@Override
	public void saveEstReq(Integer eid, String cid) {
		Estimation estimation = estRepo.findById(eid).get();
		Company company  = companyRepo.findById(cid).get();
		Esti_request estReq = new Esti_request();
		estReq.setEstimation(estimation);
		estReq.setCompany(company);
		esti_reqRepo.save(estReq);
	}

	@Autowired
	ChattingRepository chatRepo;
	
	//업체에게 온 견적 상세보기에서 채팅하기 클릭 시 chat테이블에 값 저장
	@Override
	public Chatting saveChatRoom(String id, String cid, String nickname) {
		
		Member member = memberRepo.findById(id).get();
		Company company  = companyRepo.findById(cid).get();
		Chatting chat = chatRepo.findByMemberAndCompany(member, company);
		
		//DB에 방이 있을 경우
		if(chat != null) {
			System.out.println("값이 존재합니다.");
			return chat; 
		}
		//DB에 방이 없을 경우 입력 후 검색 
		System.out.println("값이 null입니다");
		Chatting chatting = new Chatting();
		chatting.setMember(member);
		chatting.setCompany(company);
		chatting.setNickname(nickname);
		chatRepo.save(chatting);
		System.out.println("값을 입력했습니다.");
		Chatting newChat = chatRepo.findByMemberAndCompany(member, company);
		System.out.println("입력한 값을 찾아왔습니다.");
		return newChat;
		
	}
	
	

	
}
