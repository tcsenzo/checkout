package com.senzo.qettal.checkout.payment;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "payment_status_transition")
public class PaymentStatusTransition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "previous_status", nullable = true)
	private PaymentStatus previousStatus;
	@Enumerated(EnumType.STRING)
	@Column(name = "next_status", nullable = false)
	private PaymentStatus nextStatus;
	@ManyToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;
	@Column(name = "created_at")
	private Instant createdAt = Instant.now();
	
	/**
	 * @deprecated Hibernate eyes only
	 */
	PaymentStatusTransition() {
	}
	
	public PaymentStatusTransition(Payment payment, PaymentStatus previousStatus, PaymentStatus nextStatus) {
		this.payment = payment;
		this.previousStatus = previousStatus;
		this.nextStatus = nextStatus;
	}

	public PaymentStatus getNextStatus() {
		return nextStatus;
	}

}
