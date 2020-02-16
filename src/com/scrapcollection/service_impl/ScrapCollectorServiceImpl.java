package com.scrapcollection.service_impl;

import java.util.List;

import com.scrapcollection.data.ScrapCollectorDao;
import com.scrapcollection.data_impl.ScrapCollectorDaoImpl;
import com.scrapcollection.domain.ScrapCollector;
import com.scrapcollection.service.ScrapCollectorService;

public class ScrapCollectorServiceImpl implements  ScrapCollectorService {

	
	ScrapCollectorDao dao=new ScrapCollectorDaoImpl();

	@Override
	public int saveScrapCollector(ScrapCollector scrapCollector) {
		// TODO Auto-generated method stub
		return dao.saveScrapCollector(scrapCollector);
	}

	@Override
	public boolean updateScrapCollector(ScrapCollector scrapCollector) {
		// TODO Auto-generated method stub
		return dao.updateScrapCollector(scrapCollector);
	}

	@Override
	public ScrapCollector findScrapCollectorById(int id) {
		// TODO Auto-generated method stub
		return dao.findScrapCollectorById(id);
	}

	@Override
	public List<ScrapCollector> findAllScrapCollector() {
		// TODO Auto-generated method stub
		return dao.findAllScrapCollector();
	}

	@Override
	public boolean deleteScrapCollector(int id) {
		// TODO Auto-generated method stub
		return dao.deleteScrapCollector(id);
	}

	@Override
	public ScrapCollector login(String email, String password) {
		// TODO Auto-generated method stub
		return dao.login(email, password);
	}

	@Override
	public List<ScrapCollector> findAllScrapCollectorByAuthority(int authority) {
		// TODO Auto-generated method stub
		return dao.findAllScrapCollectorByAuthority(authority);
	}

	

	@Override
	public boolean updateScrapCollectorStatus(int id) {
		// TODO Auto-generated method stub
		return dao.updateScrapCollectorStatus(id);
	}

	@Override
	public List<ScrapCollector> findAllScrapCollectorByAuthorityandStatus(int authority) {
		// TODO Auto-generated method stub
		return dao.findAllScrapCollectorByAuthorityandStatus(authority);
	}
	 
}
