package com.billsharingapp.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.billsharingapp.dao.BillDao;
import com.billsharingapp.dao.BillSharingAppDao;
import com.billsharingapp.dao.GroupDao;
import com.billsharingapp.dao.impl.BillDaoImpl;
import com.billsharingapp.dao.impl.BillSharingAppDaoImpl;
import com.billsharingapp.dao.impl.GroupDaoImpl;
import com.billsharingapp.models.Bill;
import com.billsharingapp.models.BillSharingApp;
import com.billsharingapp.models.Group;
import com.billsharingapp.models.ShareType;
import com.billsharingapp.models.User;
import com.billsharingapp.repository.BillSharingAppRepository;

public class BillSharingApplication {

	private static Scanner sc = new Scanner(System.in);
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	private static BillSharingAppDao billSharingAppDao = new BillSharingAppDaoImpl();

	private static GroupDao groupDao = new GroupDaoImpl();

	private static BillDao billDao = new BillDaoImpl();

	public static void main(String[] args) throws IOException {

		BillSharingApp billSharingApp = BillSharingAppRepository.createIntialData();

		int userChoice;

		boolean quit = false;
		do {
			displayWelcomeMenu();
			userChoice = takeInput(0, 101);
			switch (userChoice) {
				case 1:
					System.out.println();
					System.out.println("User Registration Page");
					System.out.println("------------------");
					System.out.println("Enter your name:");
					String name = reader.readLine();
					System.out.println("Enter your address:");
					String address = reader.readLine();
					User registeredUser = billSharingAppDao.registerAnUser(name, address);
					System.out.println();
					System.out.println("Hi " + registeredUser.getName() + ","
							+ "Welcaome to Bill Sharing Application");
					System.out.println("Your UserId is : " + registeredUser.getUserId()
							+ ".Please use this to Login.");
					break;
				case 2:
					System.out.println();
					System.out.println("Add a User into Group");
					System.out.println("Enter the groupId:");
					String groupId = reader.readLine();
					System.out.println("Enter the userId which needs to be added:");
					String userId = reader.readLine();
					groupDao.addUserToGroup(groupId, userId);
					break;
				case 3:
					System.out.println();
					System.out.println("Add a Bill");
					System.out.println("Enter the Total amount");
					Double total = sc.nextDouble();
					System.out.println("Enter the groupId");
					String group = reader.readLine();

					System.out.println("Enter the shareType: 1 for Equally/ 0 for not equally");
					int sharetype = sc.nextInt();

					System.out.println("Enter the number of users who paid");
					int number = sc.nextInt();
					Set<String> userIds = new HashSet<>();

					HashMap<String, Double> userContrs = new HashMap<>();

					for (int i = 0; i < number; i++) {
						System.out.println("Enter the userId");
						String user = reader.readLine();
						userIds.add(user);

						System.out.println("Enter the contribution");
						Double contr = sc.nextDouble();
						userContrs.put(user, contr);
					}
					if (sharetype == 1) {
						billDao.addABill2(total, ShareType.EQUALLY, group, userContrs);
					} else {
						billDao.addABill2(total, ShareType.UNEQUALLY, group, userContrs);
					}

					break;
				case 4:
					System.out.println("Enter the billId you want to modify");
					String billIdToModify = reader.readLine();
					System.out.println("Enter the amount needs to be modified");
					double amount = sc.nextDouble();

					System.out.println("Enter the userId to add");
					String userIdToadd = reader.readLine();
					Set<String> users = new HashSet<>();
					users.add(userIdToadd);

					billDao.modifyABill(billIdToModify, amount, users);
					break;
				case 5:
					System.out.println("Enter the billId");
					String billIdToDelete = reader.readLine();
					billDao.deleteABill(billIdToDelete);
					break;
				case 8:
					System.out.println("Users:");
					List<User> userList = billSharingAppDao.getAllUsers();
					for (User user : userList) {
						System.out.println(user.toString());
					}
					System.out.println();
					break;
				case 9:
					System.out.println("Groups:");
					List<Group> groups = billSharingAppDao.getAllGroups();
					for (Group group1 : groups) {
						System.out.println(group1.toString());
					}
					System.out.println();
					break;
				case 10:
					System.out.println("Bills:");
					List<Bill> bills = billSharingAppDao.getAllBills();
					for (Bill bill : bills) {
						System.out.println(bill.toString());
					}
					System.out.println();
					break;
				case 11:
					System.out.println("Enter the billId");
					String bbill = reader.readLine();
					billDao.personWiseBalancesInBill(bbill);
					System.out.println();
					break;
				case 100:
					quit = true;
					break;
				default:
					System.out.println("Invalid Option Selected.");
					break;
			}

		} while (!quit);

	}



	public static void displayWelcomeMenu() {

		System.out.println();
		System.out.println("Welcome to Bill Sharing Application");
		System.out.println("-------------------------------");
		System.out.println("1) Register a user");
		System.out.println("2) Add a user into a Group");
		System.out.println("3) Add a Bill");
		System.out.println("4) Modify a Bill");
		System.out.println("5) Delete a Bill");
		System.out.println("6) Exclude yourself from Bill");
		System.out.println("7) Group wise bill");
		System.out.println("8) List All Users");
		System.out.println("9) List All Groups");
		System.out.println("10) List All Bills");
		System.out.println("11) Person wise share in Bill");
		System.out.println("100) Quit");
		System.out.println();

	}

	public static int takeInput(int min, int max) {
		String choice;
		Scanner input = new Scanner(System.in);

		while (true) {
			System.out.println("\nEnter your Choice: ");

			choice = input.next();

			if ((!choice.matches(".*[a-zA-Z]+.*"))
					&& (Integer.parseInt(choice) > min && Integer.parseInt(choice) < max)) {
				return Integer.parseInt(choice);
			}

			else
				System.out.println("\nInvalid Input.");
		}


	}
}
