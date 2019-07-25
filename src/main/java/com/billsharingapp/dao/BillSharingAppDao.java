package com.billsharingapp.dao;

import java.util.List;

import com.billsharingapp.models.Bill;
import com.billsharingapp.models.Group;
import com.billsharingapp.models.User;

public interface BillSharingAppDao {
	
	
	public boolean checkWhetherUserExists(String userId);
	
	
	public User registerAnUser(String name,String address);
	
	
	public List<User> getAllUsers();
	
	
	public List<Group> getAllGroups();
	
	
	public List<Bill> getAllBills();

}
