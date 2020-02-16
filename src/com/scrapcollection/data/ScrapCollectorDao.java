package com.scrapcollection.data;

import java.util.List;

import com.scrapcollection.domain.ScrapCollector;
import com.scrapcollection.domain.ScrapType;

public interface ScrapCollectorDao {
	
	public int saveScrapCollector(ScrapCollector scrapCollector);
	public boolean updateScrapCollector(ScrapCollector scarpCollector);
	public ScrapCollector findScrapCollectorById(int id);
	public List<ScrapCollector> findAllScrapCollector();
	public boolean deleteScrapCollector(int id);
	public boolean updateTaskCompletionStatus(int status);
	public ScrapCollector login(String email, String password);
	public List<ScrapCollector> findAllScrapCollector(int status);
	public List<ScrapCollector> findAllScrapCollectorByAuthority(int authority);
	public List<ScrapCollector> findAllScrapCollectorByAuthorityandStatus(int authority);
	
	public boolean updateScrapCollectorStatus(int id);
	

}
