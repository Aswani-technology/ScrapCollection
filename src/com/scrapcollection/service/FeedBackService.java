package com.scrapcollection.service;

import java.util.List;

import com.scrapcollection.domain.FeedBack;

public interface FeedBackService {
	
	
	public int saveFeedBack(FeedBack feedBack);

	public boolean updateFeedBack(FeedBack feedBack);

	public FeedBack findFeedBackById(int id);

	public List<FeedBack> findAllFeedBack();

	public boolean deleteFeedBack(int id);
	
	public List<FeedBack> findAllFeedBackByUser(int user);


}
