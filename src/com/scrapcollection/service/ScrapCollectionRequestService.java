package com.scrapcollection.service;

import java.util.List;

import com.scrapcollection.domain.ScrapCollectionRequest;
import com.scrapcollection.domain.ScrapCollector;


 
public interface ScrapCollectionRequestService {


	public int saveScrapCollectionRequest(ScrapCollectionRequest ScrapCollectionRequest);

	public boolean updateScrapCollectionRequest(ScrapCollectionRequest ScrapCollectionRequest);

	public ScrapCollectionRequest findScrapCollectionRequestById(int id);

	public List<ScrapCollectionRequest> findAllScrapCollectionRequest();

	public boolean deleteScrapCollectionRequest(int id);
	
	public List<ScrapCollectionRequest> findAllScrapCollectionRequestByAuthority(int authority);
	
	public List<ScrapCollectionRequest> findAllScrapCollectionRequestAllFields(int aid);
	
	public ScrapCollectionRequest findScrapCollectionRequestByIdAll(int id);

	public boolean updateScrapCollectionRequestStatus(int id);
	
	public List<ScrapCollectionRequest> findScrapCollectionRequestByAuthorityStatus(int authority_id,int status);
	

}
