package com.scrapcollection.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.scrapcollection.domain.ScrapCollectionRequest;
import com.scrapcollection.domain.ScrapRequest_ScrapType;
import com.scrapcollection.domain.ScrapType;

public interface ScrapRequest_ScrapTypeService {

	
	public Map<ScrapCollectionRequest, List<ScrapType>> findAll();
	
	public Set< ScrapRequest_ScrapType> findAllScrapRequest_Type();
	
	public Set< ScrapRequest_ScrapType> findAllScrapRequest_TypeByScrapCollector(int scrapcollector);
}
