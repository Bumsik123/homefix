package com.homefix.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.homefix.domain.Brag;
import com.homefix.domain.Company;
import com.homefix.domain.ElasticBrag;
import com.homefix.domain.Member;

public interface BragService {
	
	// 인테리어 자랑 작성하기
	public void saveBrag(Brag brag, String id, String cid);
	
	// 인테리어 자랑 리스트
	//public List<Brag> getBragList(Brag brag);
	public List<ElasticBrag> getBragByKeyword(String id, String loc, 
			String family, String hometype, String sort, Integer page);
	
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
	Set<Company> getContractList(String id);

	// 개인 마이페이지 후기목록 보이기용
	public List<Brag> bMyPageList(Member mem);
	
	//개인 마이페이지 후기목록 페이징
	public Page<Brag> getBragList(String id,Integer page);

	//개인 마이페이지 후기목록 페이징 2
	public long countBragList(String id);


}
