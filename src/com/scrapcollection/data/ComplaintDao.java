package com.scrapcollection.data;

import java.util.List;

import com.scrapcollection.domain.Complaint;

public interface ComplaintDao {

	

	public int saveComplaint(Complaint complaint);
	public boolean updateComplaint(Complaint complaint);
	public Complaint findComplaintById(int id);
	public List<Complaint> findAllComplaint();
	public boolean deleteComplaint(int id);
}
