package com.scrapcollection.data;

import java.util.List;

import com.scrapcollection.domain.FeedBack;

 

public interface FeedBackDao {

	public int saveFeedBack(FeedBack Feedback);
	public boolean updateFeedBack(FeedBack Feedback);
	public FeedBack findFeedBackById(int id);
	public List<FeedBack> findAllFeedBack();
	public boolean deleteFeedBack(int id);
	public List<FeedBack> findAllFeedBacks(int user);
	
}
