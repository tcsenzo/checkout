package com.senzo.qettal.checkout.ticket;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.senzo.qettal.checkout.address.Address;
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
	private Instant createdAt = Instant.now();
	private String hash;
	
	/**
	 * @deprecated JPA eyes only
	 */
	Ticket() {
	}

	public Ticket(String hash, PurchaseItem purchaseItem) {
		this.hash = hash;
		this.purchaseItem = purchaseItem;
	}

	public String getOwnerName() {
		return purchaseItem.getOwnerName();
	}

	public String getTheaterName() {
		return purchaseItem.getTheaterName();
	}

	public Address getTheaterAddress() {
		return purchaseItem.getTheaterAddress();
	}

	public String getEventName() {
		return purchaseItem.getEventName();
	}
	
	public String getEventDescription() {
		return purchaseItem.getEventDescription();
	}

	public BigDecimal getPrice() {
		return purchaseItem.getPrice();
	}

	public Instant getScheduledDate() {
		return purchaseItem.getScheduledDate();
	}

	public String getHash() {
		return hash;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, hash);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(id, other.id)
				&& Objects.equals(hash, other.hash) ;
	}
	
	

}
