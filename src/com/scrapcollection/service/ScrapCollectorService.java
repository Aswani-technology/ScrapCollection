package com.scrapcollection.service;

import java.util.List;

import com.scrapcollection.domain.ScrapCollector;

public interface ScrapCollectorService {

	public int saveScrapCollector(ScrapCollector scrapCollector);

	public boolean updateScrapCollector(ScrapCollector scrapCollector);

	public ScrapCollector findScrapCollectorById(int id);

	public List<ScrapCollector> findAllScrapCollector();

	public boolean deleteScrapCollector(int id);
	
	public ScrapCollector login(String email, String password);
	
	public List<ScrapCollector> findAllScrapCollectorByAuthority( int authority);
	
	public List<ScrapCollector> findAllScrapCollectorByAuthorityandStatus( int authority);
	
	public boolean updateScrapCollectorStatus(int id);
}
