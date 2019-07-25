package com.billsharingapp.utils;

import java.util.UUID;

public class BillSharingAppUtils {
	
	/**
	 * 
	 * Get the random ID
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().toUpperCase().replace("-", "").substring(0, 5);
	}

}
