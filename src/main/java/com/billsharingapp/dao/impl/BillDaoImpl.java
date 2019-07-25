package com.billsharingapp.dao.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.billsharingapp.dao.BillDao;
import com.billsharingapp.dao.BillSharingAppDao;
import com.billsharingapp.dao.GroupDao;
import com.billsharingapp.models.Bill;
import com.billsharingapp.models.BillSharingApp;
import com.billsharingapp.models.Group;
import com.billsharingapp.models.ShareType;
import com.billsharingapp.models.User;

public class BillDaoImpl implements BillDao {

	BillSharingAppDao billSharingDao = new BillSharingAppDaoImpl();

	GroupDao groupDao = new GroupDaoImpl();

	@Override
	public boolean addABill(Double totalAmount, ShareType shareType, String groupId,
			Set<String> userIds, HashMap<String, Double> userContrs) {

		BillSharingApp billSharingApp = BillSharingApp.getInstance();

		// Validate GroupId
		if (!groupDao.checkWhetherGroupExists(groupId)) {
			System.out.println("Group Id does not exists");
			return false;
		}

		// Validate UserIds
		for (String user : userIds) {
			if (!billSharingDao.checkWhetherUserExists(user)) {
				System.out.println("UserIds does not exists");
				return false;
			}
		}

		Group group = billSharingApp.getGroups().stream()
				.filter(g -> g.getGroupId().equals(groupId)).collect(Collectors.toList()).get(0);

		Set<User> usersToAddInBill = new HashSet<>();
		for (User userId : group.getUsers()) {
			usersToAddInBill.add(userId);
		}

		HashMap<User, Double> userContributions = new HashMap<>();
		for (Map.Entry<String, Double> entry : userContrs.entrySet()) {
			User user = billSharingApp.getUsers().stream()
					.filter(u -> u.getUserId().equals(entry.getKey())).collect(Collectors.toList())
					.get(0);

			if (!usersToAddInBill.contains(user)) {
				System.out.println("User is not part of Group");
				return false;
			}
			userContributions.put(user, entry.getValue());

		}

		Bill bill = new Bill(totalAmount, shareType, group, userContributions);
		billSharingApp.getBills().add(bill);

		return false;
	}

	@Override
	public boolean modifyABill(String billId, Double totalAmount, Set<String> userIds) {

		if (!checkWhetherBillExists(billId)) {
			System.out.println("Bill Id does not exists");
			return false;
		}

		// Validate UserIds
		for (String user : userIds) {
			if (!billSharingDao.checkWhetherUserExists(user)) {
				System.out.println("UserIds does not exists");
				return false;
			}
		}
		BillSharingApp billSharingApp = BillSharingApp.getInstance();

		Bill bill = billSharingApp.getBills().stream().filter(g -> g.getBillId().equals(billId))
				.collect(Collectors.toList()).get(0);

		Group group = billSharingApp.getGroups().stream()
				.filter(g -> g.getGroupId().equals(bill.getGroup().getGroupId()))
				.collect(Collectors.toList()).get(0);

		if (userIds.size() > 0) {
			Set<User> usersToAdd = new HashSet<>();
			for (String userId : userIds) {
				User user =
						billSharingApp.getUsers().stream().filter(u -> u.getUserId().equals(userId))
								.collect(Collectors.toList()).get(0);

				if (!group.getUsers().contains(user)) {
					System.out.println("User cannot be added as he is not part of group");
					return false;
				}

				usersToAdd.add(user);
			}
			// bill.getUsers().addAll(usersToAdd);
		}

		bill.setTotalAmount(totalAmount);

		return true;
	}

	@Override
	public boolean deleteABill(String billId) {

		if (!checkWhetherBillExists(billId)) {
			System.out.println("Bill Id does not exists");
			return false;
		}

		BillSharingApp billSharingApp = BillSharingApp.getInstance();

		Bill bill = billSharingApp.getBills().stream().filter(g -> g.getBillId().equals(billId))
				.collect(Collectors.toList()).get(0);

		billSharingApp.getBills().remove(bill);

		return false;
	}


	@Override
	public boolean checkWhetherBillExists(String billId) {
		BillSharingApp billSharingApp = BillSharingApp.getInstance();


		Long billCount = billSharingApp.getBills().stream()
				.filter(u -> u.getBillId().equals(billId)).distinct().count();

		if (billCount > 0) {
			return true;
		}

		return false;
	}


	@Override
	public boolean addABill2(Double totalAmount, ShareType shareType, String groupId,
			HashMap<String, Double> userContrs) {

		BillSharingApp billSharingApp = BillSharingApp.getInstance();

		// Validate GroupId
		if (!groupDao.checkWhetherGroupExists(groupId)) {
			System.out.println("Group Id does not exists");
			return false;
		}


		Group group = billSharingApp.getGroups().stream()
				.filter(g -> g.getGroupId().equals(groupId)).collect(Collectors.toList()).get(0);

		HashMap<User, Double> userContributions = new HashMap<>();
		for (Map.Entry<String, Double> entry : userContrs.entrySet()) {
			User user = billSharingApp.getUsers().stream()
					.filter(u -> u.getUserId().equals(entry.getKey())).collect(Collectors.toList())
					.get(0);

			if (!billSharingDao.checkWhetherUserExists(user.getUserId())) {
				System.out.println("UserId" + user.getUserId() + "does not exists");
				return false;
			}

			if (!group.getUsers().contains(user)) {
				System.out.println("User " + user.toString() + "is not part of Group");
				return false;
			}
			userContributions.put(user, entry.getValue());

		}
		for (User user : group.getUsers()) {
			if (!userContrs.keySet().contains(user.getUserId())) {
				userContributions.put(user, 0.0);
			}
		}

		Bill bill = new Bill(totalAmount, shareType, group, userContributions);
		billSharingApp.getBills().add(bill);

		return false;
	}

	@Override
	public void personWiseBalancesInBill(String billId) {

		if (!checkWhetherBillExists(billId)) {
			System.out.println("Bill Id does not exists");
			return;
		}

		BillSharingApp billSharingApp = BillSharingApp.getInstance();

		Bill bill = billSharingApp.getBills().stream().filter(g -> g.getBillId().equals(billId))
				.collect(Collectors.toList()).get(0);

		Double divededAmount = bill.getTotalAmount() / bill.getGroup().getUsers().size();
		Double totalAmount = bill.getTotalAmount();
		HashMap<User, Double> userContributions = bill.getUserContributions();
		for (Map.Entry<User, Double> entry : userContributions.entrySet()) {
			Group gr = bill.getGroup();
			User us = entry.getKey();
			double value = 0;
			for (User ur : gr.getUsers()) {
				if (us != ur) {
					value += userContributions.get(ur);
				}
			}

			if (userContributions.get(us) > value) {
				System.out.println(
						"User " + us.getUserId() + " gets" + (totalAmount - divededAmount - value));
			} else {
				System.out.println("User " + us.getUserId() + " pays"
						+ Math.abs(totalAmount - divededAmount - value));
			}
		}
	}


}
