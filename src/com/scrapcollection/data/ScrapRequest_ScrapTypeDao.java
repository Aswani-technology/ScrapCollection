package com.scrapcollection.data;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.scrapcollection.domain.ScrapCollectionRequest;
import com.scrapcollection.domain.ScrapRequest_ScrapType;
import com.scrapcollection.domain.ScrapType;

public interface ScrapRequest_ScrapTypeDao {
	
	public List<ScrapRequest_ScrapType> findAll(int authority);
	
	public ScrapRequest_ScrapType findById(int id);
	
	public Map<ScrapCollectionRequest, List<ScrapType>> findAll();
	
	public Set< ScrapRequest_ScrapType> findAllScrapRequest_Type();
	
	public Set< ScrapRequest_ScrapType> findAllScrapRequest_TypeByScrapCollector(int scrapcollector);
	
	
	public ScrapRequest_ScrapType findRequestTypeById(int id);
}
