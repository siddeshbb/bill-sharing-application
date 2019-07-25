package com.billsharingapp.dao.impl;

import java.util.stream.Collectors;

import com.billsharingapp.dao.BillSharingAppDao;
import com.billsharingapp.dao.GroupDao;
import com.billsharingapp.models.BillSharingApp;
import com.billsharingapp.models.Group;
import com.billsharingapp.models.User;

public class GroupDaoImpl implements GroupDao{

	BillSharingAppDao billSharingDao =  new BillSharingAppDaoImpl();
	
	@Override
	public void addUserToGroup(String groupId,String userId) {
		
		if(!checkWhetherGroupExists(groupId)) {
			System.out.println( "Group Id does not exists");			
			return;
		}
		
		if(!billSharingDao.checkWhetherUserExists(userId)) {
			System.out.println("UserId does not exists");
			return;
		}
		
		BillSharingApp billSharingApp = BillSharingApp.getInstance();
		User user = billSharingApp.getUsers().stream().filter(u -> u.getUserId().equals(userId)).collect(Collectors.toList()).get(0);
		
		Group group = billSharingApp.getGroups().stream().filter(g -> g.getGroupId().equals(groupId)).collect(Collectors.toList()).get(0);
		
		group.getUsers().add(user);
		
		
	}

	@Override
	public boolean checkWhetherGroupExists(String groupId) {
		BillSharingApp billSharingApp = BillSharingApp.getInstance();

		Long userCount = billSharingApp.getGroups().stream().filter(u -> u.getGroupId().equals(groupId))
				.distinct().count();

		if (userCount > 0) {
			return true;
		}

		return false;
	}

}
