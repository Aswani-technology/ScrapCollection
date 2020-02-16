package com.scrapcollection.service;

import java.util.List;

import com.scrapcollection.domain.ScrapType;

public interface ScrapTypeService {

	public int saveBookType(ScrapType bookType);

	public boolean updateScrapType(ScrapType bookType);

	public ScrapType findScrapTypeById(int id);

	public List<ScrapType> findAllScrapType();

	public boolean deleteScrapType(int id);
}
