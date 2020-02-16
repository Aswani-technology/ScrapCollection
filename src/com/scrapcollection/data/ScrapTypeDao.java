package com.scrapcollection.data;

import java.util.List;

import com.scrapcollection.domain.ScrapType;

 

public interface ScrapTypeDao {

	public int saveScrapType(ScrapType bookType);
	public boolean updateScrapType(ScrapType bookType);
	public ScrapType findScrapTypeById(int id);
	public List<ScrapType> findAllScrapType();
	public boolean deleteScrapType(int id);
}
