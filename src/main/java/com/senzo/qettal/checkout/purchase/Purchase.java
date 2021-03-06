package com.senzo.qettal.checkout.purchase;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.moip.resource.Order;

import com.senzo.qettal.checkout.address.Address;
import com.senzo.qettal.checkout.payment.Payment;
import com.senzo.qettal.checkout.payment.Payments;
import com.senzo.qettal.checkout.users.User;

@Entity
@Table(name = "purchase")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="purchase")	
	private List<PurchaseItem> items = new ArrayList<>();
	@Column(name = "theater_id")
	private Long theaterId;
	@Column(name = "theater_name")
	private String theaterName;
	@Embedded
	private Address theaterAddress;
	@Column(name = "event_name")
	private String eventName;
	@Column(name = "event_description")
	private String eventDescription;
	@Column(name = "event_image")
	private String eventImage;
	@Column(name = "scheduled_date")
	private Instant scheduledDate;
	@Column(name = "reference_id")
	private String referenceId;
	@OneToMany(mappedBy="purchase", fetch=FetchType.EAGER)
	private List<Payment> payment = new ArrayList<>();
	@Column(name = "unique_id")
	private String uniqueId = UUID.randomUUID().toString();
	@Column(name = "created_at")
	private Instant createdAt = Instant.now();

	/**
	 * @deprecated Hibernate eyes only
	 */
	Purchase() {
	}

	public Purchase(User owner, String eventName, String eventDescription, String eventImage, Instant scheduledDate,
			Long theaterId, String theaterName, Address theaterAddress) {
		this.owner = owner;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventImage = eventImage;
		this.scheduledDate = scheduledDate;
		this.theaterName = theaterName;
		this.theaterId = theaterId;
		this.theaterAddress = theaterAddress;
	}

	public Long getId() {
		return id;
	}

	public Set<PurchaseItem> getItems() {
		return new HashSet<>(items);
	}

	public void addMoipInfo(Order createdOrder, Purchases purchases) {
		this.referenceId = createdOrder.getId();
		purchases.update(this);
	}

	public String getReferenceId() {
		return referenceId;
	}

	public boolean isOwnedBy(User user) {
		return owner.equals(user);
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public Optional<Payment> getPayment() {
		if(payment.isEmpty()){
			return Optional.empty();
		}
		return Optional.ofNullable(payment.get(0));
	}

	public Payment pay(Payments payments) {
		Payment payment = new Payment(this);
		this.payment = Arrays.asList(payment);
		payments.save(payment);
		return payment;
	}

	public void addItems(List<PurchaseItem> items) {
		this.items.addAll(items);
	}
	
	public String getOwnerName(){
		return owner.getName();
	}

	public String getEventName() {
		return eventName;
	}
	
	public String getEventDescription() {
		return eventDescription;
	}

	public String getEventImage() {
		return eventImage;
	}
	
	public String getTheaterName() {
		return theaterName;
	}
	
	public Address getTheaterAddress() {
		return theaterAddress;
	}

	public Instant getScheduledDate() {
		return scheduledDate;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Long getTheaterId() {
		return theaterId;
	}

	public User getOwner() {
		return owner;
	}
}
