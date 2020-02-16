package com.scrapcollection.data;

import java.util.*;

import com.scrapcollection.domain.ScrapCollectionRequest;
import com.scrapcollection.domain.ScrapRequestList;


public interface ScrapCollectionRequestDao {

	public int saveScrapCollectionRequest(ScrapCollectionRequest scrapCollectionRequest);
	public boolean updateScrapCollectionRequest(ScrapCollectionRequest scrapCollectionRequest);
	public ScrapCollectionRequest findScrapCollectionRequestById(int id);
	
	public ScrapCollectionRequest findScrapCollectionRequestByIdAll(int id);
	
	public List<ScrapCollectionRequest> findAllScrapCollectionRequest();
	public boolean deleteScrapCollectionRequest(int id);
	
	public List<ScrapCollectionRequest> findScrapCollectionRequestByAuthority(int authority_id);
	public List<ScrapCollectionRequest> findScrapCollectionRequestByAuthorityStatus(int authority_id,int status);

	public List<ScrapCollectionRequest> getUniqueScrapRequests();
	
	public List<ScrapCollectionRequest> findAllScrapCollectionRequestAllFields(int aid);
	
	public boolean updateScrapCollectionRequestStatus(int id);
	
	

	
	
}
