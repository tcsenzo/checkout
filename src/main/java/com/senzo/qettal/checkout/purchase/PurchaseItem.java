package com.senzo.qettal.checkout.purchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_item")
public class PurchaseItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Long quantity;
	@Column(name="unit_price")
	private BigDecimal unitPrice;
	@ManyToOne
	@JoinColumn(name = "purchase_id", nullable = false)
	private Purchase purchase;
	@Column(name="created_at")
	private LocalDateTime createdAt = LocalDateTime.now();

	/**
	 * @deprecated Hibernate eyes only
	 */
	PurchaseItem() {
	}

	public PurchaseItem(String name, String description, Long quantity, BigDecimal unitPrice, Purchase purchase) {
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.purchase = purchase;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Long getQuantity() {
		return quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public Long getId() {
		return id;
	}
	
	public String getPurchaseUniqueId() {
		return purchase.getUniqueId();
	}

}
