package com.billsharingapp.models;

import java.util.HashSet;
import java.util.Set;

import com.billsharingapp.utils.BillSharingAppUtils;

public class Group {
	
	String groupId;
	
	Set<User> users;

	public  Group() {
		this.groupId = BillSharingAppUtils.getUUID();
		this.users = new HashSet<>();
	}
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", users=" + users + "]";
	}
	

}
