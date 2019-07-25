package com.billsharingapp.models;

import java.util.ArrayList;
import java.util.List;

public class BillSharingApp {
	
	
	 List<Group> groups;
	 
	 List<User> users;
	 
	 List<Bill> bills;
	 
	 private static BillSharingApp billSharingApp;
	 
	 private BillSharingApp() {
		 this.groups = new ArrayList<>();
		 this.users = new ArrayList<>();
		 this.bills = new ArrayList<>();
	 }
	 
	 public static BillSharingApp getInstance() {
		 if(billSharingApp == null) {
			 billSharingApp = new BillSharingApp();
		 }
		 return billSharingApp;
	 }

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

}
