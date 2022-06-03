package com.homefix.service;

import java.util.List;

import com.homefix.domain.Brag;
import com.homefix.domain.Contract;
import com.homefix.domain.Member;

public interface BragService {
	
	// 인테리어 자랑(후기) 작성하기
	public void saveBrag(Brag brag, String id, String cid);
	
	// 인테리어 자랑 리스트
	public List<Brag> getBragList(Brag brag);
	
	// 인테리어 자랑 상세
	public Brag getBrag(Brag brag, String id);
	
	// 인테리어 자랑 삭제
	public void deleteBrag(Brag brag, String id);
	
	// 좋아요 입력
	public void savePrefer(Brag brag, String id);
	
	// 좋아요 취소
	public void deletePrefer(Brag brag, String id);
	
	// 신고하기
	public String saveReport(Member id, String reporter, String reason);
	
	// 계약완료 업체리스트
	List<Contract> getContractList(String id);
	
	
	
	
}
