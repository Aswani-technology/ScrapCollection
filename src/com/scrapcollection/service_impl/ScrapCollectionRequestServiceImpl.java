package com.scrapcollection.service_impl;

import java.util.List;

import com.scrapcollection.data.ScrapCollectionRequestDao;
import com.scrapcollection.data_impl.ScrapCollectionRequestDaoImpl;
import com.scrapcollection.domain.ScrapCollectionRequest;

import com.scrapcollection.service.ScrapCollectionRequestService;

public class ScrapCollectionRequestServiceImpl  implements ScrapCollectionRequestService{

	
	ScrapCollectionRequestDao dao=new ScrapCollectionRequestDaoImpl();

	@Override
	public int saveScrapCollectionRequest(ScrapCollectionRequest scrapCollectionRequest) {
		// TODO Auto-generated method stub
		return dao.saveScrapCollectionRequest(scrapCollectionRequest);
	}

	@Override
	public boolean updateScrapCollectionRequest(ScrapCollectionRequest scrapCollectionRequest) {
		// TODO Auto-generated method stub
		return dao.updateScrapCollectionRequest(scrapCollectionRequest);
	}

	@Override
	public ScrapCollectionRequest findScrapCollectionRequestById(int id) {
		// TODO Auto-generated method stub
		return dao.findScrapCollectionRequestById(id);
	}

	@Override
	public List<ScrapCollectionRequest> findAllScrapCollectionRequest() {
		// TODO Auto-generated method stub
		return dao.findAllScrapCollectionRequest();
	}

	@Override
	public boolean deleteScrapCollectionRequest(int id) {
		// TODO Auto-generated method stub
		return dao.deleteScrapCollectionRequest(id);
	}

	@Override
	public List<ScrapCollectionRequest> findAllScrapCollectionRequestByAuthority(int authority) {
		// TODO Auto-generated method stub
		return dao.findScrapCollectionRequestByAuthority(authority);
	}

	@Override
	public List<ScrapCollectionRequest> findAllScrapCollectionRequestAllFields(int aid) {
		// TODO Auto-generated method stub
		return dao.findAllScrapCollectionRequestAllFields(aid);
	}

	@Override
	public boolean updateScrapCollectionRequestStatus(int id) {
		// TODO Auto-generated method stub
		return dao.updateScrapCollectionRequestStatus(id);
	}

	@Override
	public List<ScrapCollectionRequest> findScrapCollectionRequestByAuthorityStatus(int authority_id, int status) {
		// TODO Auto-generated method stub
		return dao.findScrapCollectionRequestByAuthorityStatus(authority_id, status);
	}

	@Override
	public ScrapCollectionRequest findScrapCollectionRequestByIdAll(int id) {
		// TODO Auto-generated method stub
		return dao.findScrapCollectionRequestByIdAll(id);
	}



	
}
