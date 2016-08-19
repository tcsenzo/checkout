package com.senzo.qettal.checkout.ticket;

import com.senzo.qettal.checkout.purchase.PurchaseItem;


public class TicketQRCode {

	private byte[] bytes;
	private PurchaseItem item;
	private String securityHash;

	public TicketQRCode(byte[] bytes, PurchaseItem item, String securityHash) {
		this.bytes = bytes;
		this.item = item;
		this.securityHash = securityHash;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public PurchaseItem getPurchaseItem() {
		return item;
	}

	public String getSecurityHash() {
		return securityHash;
	}
	
}
