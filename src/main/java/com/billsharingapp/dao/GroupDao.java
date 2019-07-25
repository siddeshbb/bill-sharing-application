package com.billsharingapp.dao;

public interface GroupDao {
	
	public boolean checkWhetherGroupExists(String groupId);
	
	public void addUserToGroup(String groupId, String userId);

}
