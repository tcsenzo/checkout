package com.senzo.qettal.checkout.payment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	@JoinColumn(name = "purchase_id")
	private Purchase purchase;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "payment_id")
	private List<PaymentStatusTransition> statuses = new ArrayList<>();
	@OneToOne
	@JoinColumn(name = "last_status_transition_id")
	private PaymentStatusTransition lastStatusTransition;
	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();
	

	/**
	 * @deprecated Hibernate eyes only
	 */
	Payment() {
	}

	public Payment(Purchase purchase) {
		this.purchase = purchase;
		this.lastStatusTransition = new PaymentStatusTransition(null, PaymentStatus.STARTED);
		this.statuses.add(lastStatusTransition);
	}

	public Long getId() {
		return id;
	}

}
