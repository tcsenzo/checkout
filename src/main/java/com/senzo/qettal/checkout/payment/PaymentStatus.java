package com.senzo.qettal.checkout.payment;

import java.util.HashMap;

public enum PaymentStatus {
	STARTED(2),
	UNDER_ANALYSIS(6),
	CANCELED(5),
	APPROVED(1, 4);
	
	private Integer[] moipStatuses;
	private static HashMap<Integer, PaymentStatus> moipMap = new HashMap<Integer, PaymentStatus>();
	
	static {
		PaymentStatus[] statuses = values();
		for (PaymentStatus paymentStatus : statuses) {
			Integer[] equivalentMoipStatuses = paymentStatus.moipStatuses;
			for (Integer moipStatus : equivalentMoipStatuses) {
				moipMap.put(moipStatus, paymentStatus);
			}
		}
	}
	
	private PaymentStatus(Integer...moipStatuses) {
		this.moipStatuses = moipStatuses;
	}
	
	public static PaymentStatus equivalentToMoip(Integer moipStatus){
		return moipMap.get(moipStatus);
	}
}
