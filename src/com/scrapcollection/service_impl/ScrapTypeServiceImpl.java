package com.scrapcollection.service_impl;

import java.util.List;

import com.scrapcollection.data.ScrapTypeDao;
import com.scrapcollection.data_impl.ScrapTypeDaoImpl;
import com.scrapcollection.domain.ScrapType;
import com.scrapcollection.service.ScrapTypeService;

public class ScrapTypeServiceImpl implements ScrapTypeService {
ScrapTypeDao dao=new ScrapTypeDaoImpl();
	@Override
	public int saveBookType(ScrapType scrapType) {
		// TODO Auto-generated method stub
		return dao.saveScrapType(scrapType);
	}

	@Override
	public boolean updateScrapType(ScrapType scrapType) {
		// TODO Auto-generated method stub
		return dao.updateScrapType(scrapType);
	}

	@Override
	public ScrapType findScrapTypeById(int id) {
		// TODO Auto-generated method stub
		return dao.findScrapTypeById(id);
	}

	@Override
	public List<ScrapType> findAllScrapType() {
		// TODO Auto-generated method stub
		return dao.findAllScrapType();
	}

	@Override
	public boolean deleteScrapType(int id) {
		// TODO Auto-generated method stub
		return dao.deleteScrapType(id);
	}

}
