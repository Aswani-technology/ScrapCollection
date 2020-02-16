package com.scrapcollection.service_impl;

import java.util.List;

import com.scrapcollection.data.ComplaintDao;
import com.scrapcollection.data_impl.ComplaintDaoImpl;
import com.scrapcollection.domain.Complaint;
import com.scrapcollection.service.ComplaintService;

public class ComplaintServiceImpl implements ComplaintService {
	
	ComplaintDao dao=new ComplaintDaoImpl();

	@Override
	public int saveComplaint(Complaint Complaint) {
		// TODO Auto-generated method stub
		return dao.saveComplaint(Complaint);
	}

	@Override
	public boolean updateComplaint(Complaint Complaint) {
		// TODO Auto-generated method stub
		return dao.updateComplaint(Complaint);
	}

	@Override
	public Complaint findComplaintById(int id) {
		// TODO Auto-generated method stub
		return dao.findComplaintById(id);
	}

	@Override
	public List<Complaint> findAllComplaint() {
		// TODO Auto-generated method stub
		return dao.findAllComplaint();
	}

	@Override
	public boolean deleteComplaint(int id) {
		// TODO Auto-generated method stub
		return dao.deleteComplaint(id);
	}
	
	

}
