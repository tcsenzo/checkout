package com.senzo.qettal.checkout.payment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.senzo.qettal.checkout.purchase.Purchase;

@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "purchase_id", nullable = false)
	private Purchase purchase;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "payment")
	private List<PaymentStatusTransition> statuses = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	@Column(name = "last_status", nullable = false)
	private PaymentStatus lastStatus = PaymentStatus.STARTED;
	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();
	
	/**
	 * @deprecated Hibernate eyes only
	 */
	Payment() {
		statuses.add(new PaymentStatusTransition(this, null, lastStatus));
	}

	public Payment(Purchase purchase) {
		this();
		this.purchase = purchase;
	}

	public Long getId() {
		return id;
	}

	public void updateStatus(PaymentStatus nextStatus, Payments payments) {
		this.statuses.add(new PaymentStatusTransition(this, lastStatus, nextStatus));
		this.lastStatus = nextStatus;
		payments.update(this);
	}

	public boolean isApproved() {
		return lastStatus.isApproved();
	}

}
