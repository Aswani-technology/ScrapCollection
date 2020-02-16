package com.scrapcollection.service_impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.scrapcollection.data.ScrapRequest_ScrapTypeDao;
import com.scrapcollection.data_impl.ScrapRequest_ScrapTypeDao_Impl;
import com.scrapcollection.domain.ScrapCollectionRequest;
import com.scrapcollection.domain.ScrapRequest_ScrapType;
import com.scrapcollection.domain.ScrapType;
import com.scrapcollection.service.ScrapRequest_ScrapTypeService;

public class ScrapRequest_ScrapTypeServiceImpl implements ScrapRequest_ScrapTypeService {

	ScrapRequest_ScrapTypeDao dao=new ScrapRequest_ScrapTypeDao_Impl();
	
	@Override
	public Map<ScrapCollectionRequest, List<ScrapType>> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Set<ScrapRequest_ScrapType> findAllScrapRequest_Type() {
		// TODO Auto-generated method stub
		return dao.findAllScrapRequest_Type();
	}

	@Override
	public Set<ScrapRequest_ScrapType> findAllScrapRequest_TypeByScrapCollector(int scrapcollector) {
		// TODO Auto-generated method stub
		return dao.findAllScrapRequest_TypeByScrapCollector(scrapcollector);
	}

}
