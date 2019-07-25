package com.billsharingapp.models;

import java.util.HashMap;
import java.util.Set;

import com.billsharingapp.utils.BillSharingAppUtils;

public class Bill {
	
	String billId;
	
	double totalAmount;
	
	ShareType shareType;
	
	Group group;
	
	HashMap<User,Double> userContributions;


	public Bill( double totalAmount, ShareType shareType, Group group,
			HashMap<User, Double> userContributions) {
		super();
		this.billId = BillSharingAppUtils.getUUID();
		this.totalAmount = totalAmount;
		this.shareType = shareType;
		this.group = group;
		this.userContributions = userContributions;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public ShareType getShareType() {
		return shareType;
	}

	public void setShareType(ShareType shareType) {
		this.shareType = shareType;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public HashMap<User, Double> getUserContributions() {
		return userContributions;
	}

	public void setUserContributions(HashMap<User, Double> userContributions) {
		this.userContributions = userContributions;
	}

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", totalAmount=" + totalAmount + ", shareType=" + shareType + ", group="
				+ group +  ", userContributions=" + userContributions + "]";
	}

}
