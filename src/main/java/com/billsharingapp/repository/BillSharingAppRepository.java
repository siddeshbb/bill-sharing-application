package com.billsharingapp.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.billsharingapp.models.Bill;
import com.billsharingapp.models.BillSharingApp;
import com.billsharingapp.models.Group;
import com.billsharingapp.models.ShareType;
import com.billsharingapp.models.User;

public class BillSharingAppRepository {
	
	
	public static BillSharingApp createIntialData() {
		
		BillSharingApp billSharingApp = BillSharingApp.getInstance();
		
		User user1 = new User("TestUser1", "Address1");
		User user2 = new User("TestUser2", "Address2");
		User user3 = new User("TestUser3", "Address3");
		
		List<User> usersList = new ArrayList<>();
		usersList.add(user1);
		usersList.add(user2);
		usersList.add(user3);
		billSharingApp.setUsers(usersList);
		
		Group group1 = new Group();
		group1.getUsers().add(user1);
		group1.getUsers().add(user2);
		
		billSharingApp.getGroups().add(group1);
		
		HashMap<User, Double> contr = new HashMap<>();
		contr.put(user1, 100.0);
		contr.put(user2, 100.0);
		
		Set<User> usersInBill = new HashSet<>();
		usersInBill.add(user1);
		usersInBill.add(user2);
		
		Bill bill = new Bill(200.0,ShareType.EQUALLY,group1,contr);
		
		billSharingApp.getBills().add(bill);
		
		
		return billSharingApp;
	}

}
