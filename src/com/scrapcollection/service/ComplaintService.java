package com.scrapcollection.service;

import java.util.List;

import com.scrapcollection.domain.Complaint;

public interface ComplaintService {
	

	public int saveComplaint(Complaint Complaint);

	public boolean updateComplaint(Complaint Complaint);

	public Complaint findComplaintById(int id);

	public List<Complaint> findAllComplaint();

	public boolean deleteComplaint(int id);

}
