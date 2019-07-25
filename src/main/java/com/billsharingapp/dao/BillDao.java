package com.billsharingapp.dao;

import java.util.HashMap;
import java.util.Set;

import com.billsharingapp.models.ShareType;

public interface BillDao {


	public boolean addABill(Double totalAmount, ShareType shareType, String groupId,
			Set<String> userIds, HashMap<String, Double> userContrs);

	public boolean modifyABill(String billId, Double totalAmount, Set<String> userIds);

	public boolean deleteABill(String billId);

	public boolean checkWhetherBillExists(String billId);


	public boolean addABill2(Double totalAmount, ShareType shareType, String groupId,
			HashMap<String, Double> userContrs);

	public void personWiseBalancesInBill(String billId);
}
