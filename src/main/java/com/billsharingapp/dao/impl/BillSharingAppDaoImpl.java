package com.billsharingapp.dao.impl;

import java.util.List;

import com.billsharingapp.dao.BillSharingAppDao;
import com.billsharingapp.models.Bill;
import com.billsharingapp.models.BillSharingApp;
import com.billsharingapp.models.Group;
import com.billsharingapp.models.User;

public class BillSharingAppDaoImpl implements BillSharingAppDao{

	@Override
	public boolean checkWhetherUserExists(String userId) {
		BillSharingApp billSharingApp = BillSharingApp.getInstance();

		Long userCount = billSharingApp.getUsers().stream().filter(u -> u.getUserId().equals(userId))
				.distinct().count();

		if (userCount > 0) {
			return true;
		}

		return false;
	}

	@Override
	public User registerAnUser(String name, String address) {
		
		BillSharingApp billSharingApp = BillSharingApp.getInstance();
		User user  = new User(name,address);
		billSharingApp.getUsers().add(user);
		
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return BillSharingApp.getInstance().getUsers();
	}

	@Override
	public List<Group> getAllGroups() {
		return BillSharingApp.getInstance().getGroups();
	}

	@Override
	public List<Bill> getAllBills() {
		return BillSharingApp.getInstance().getBills();
	}

}
