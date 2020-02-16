package com.scrapcollection.service_impl;

import java.util.List;

import com.scrapcollection.data.FeedBackDao;
import com.scrapcollection.data_impl.FeedBackDaoImpl;
import com.scrapcollection.domain.FeedBack;
import com.scrapcollection.service.FeedBackService;

public class FeedBackServiceImpl implements FeedBackService {
	FeedBackDao dao=new FeedBackDaoImpl();

	@Override
	public int saveFeedBack(FeedBack FeedBack) {
		// TODO Auto-generated method stub
		return dao.saveFeedBack(FeedBack);
	}

	@Override
	public boolean updateFeedBack(FeedBack FeedBack) {
		// TODO Auto-generated method stub
		return dao.updateFeedBack(FeedBack);
	}

	@Override
	public FeedBack findFeedBackById(int id) {
		// TODO Auto-generated method stub
		return dao.findFeedBackById(id);
	}

	@Override
	public List<FeedBack> findAllFeedBack() {
		// TODO Auto-generated method stub
		return dao.findAllFeedBack();
	}

	@Override
	public boolean deleteFeedBack(int id) {
		// TODO Auto-generated method stub
		return dao.deleteFeedBack(id);
	}

	@Override
	public List<FeedBack> findAllFeedBackByUser(int user) {
		// TODO Auto-generated method stub
		return dao.findAllFeedBacks(user);
	}

}
