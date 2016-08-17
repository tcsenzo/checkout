package com.senzo.qettal.checkout.tickets;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.senzo.qettal.checkout.purchase.PurchaseItem;


@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "purchase_item_id", nullable = false)
	private PurchaseItem purchaseItem;
	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();
	private String hash;

	public Ticket(String hash, PurchaseItem purchaseItem) {
		this.hash = hash;
		this.purchaseItem = purchaseItem;
	}
	

}
