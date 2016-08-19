package com.senzo.qettal.checkout.purchase;

import static javax.persistence.EnumType.STRING;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.senzo.qettal.checkout.address.Address;
import com.senzo.qettal.checkout.ticket.TicketType;

@Entity
@Table(name = "purchase_item")
public class PurchaseItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="price")
	private BigDecimal price;
	@Column(name="ticket_type")
	@Enumerated(STRING)
	private TicketType type;
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

	public PurchaseItem(BigDecimal price, TicketType type, Purchase purchase) {
		this.price = price;
		this.type = type;
		this.purchase = purchase;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Long getId() {
		return id;
	}
	
	public String getPurchaseUniqueId() {
		return purchase.getUniqueId();
	}

	public String getOwnerName() {
		return purchase.getOwnerName();
	}

	public String getTheaterName() {
		return purchase.getTheaterName();
	}

	public Address getTheaterAddress() {
		return purchase.getTheaterAddress();
	}

	public String getEventName() {
		return purchase.getEventName();
	}
	
	public String getEventDescription() {
		return purchase.getEventDescription();
	}

	public LocalDateTime getScheduledDate() {
		return purchase.getScheduledDate();
	}
	
	public TicketType getType() {
		return type;
	}

}
