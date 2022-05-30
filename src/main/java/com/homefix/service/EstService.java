package com.homefix.service;

import java.util.List;

import com.homefix.domain.Esti_request;
import com.homefix.domain.Estimation;

public interface EstService {
	
	//견적 상세목록 불러오기
	public Estimation getEstDetail(String id);
	
	public List<Estimation> getCEst(String id);
	
	public List<Estimation> getMEstimation(String id);
	
	//내(고객) 견적 리스트 상세보기
	public Estimation getMEDetail(Integer id);
	
	//내(고객)리스트 상세보기 회사리스트 보기
	

}