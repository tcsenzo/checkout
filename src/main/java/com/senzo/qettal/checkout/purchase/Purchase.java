package com.senzo.qettal.checkout.purchase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "purchase_id", nullable=false)
	private List<PurchaseItem> items;
	@Column(name="reference_id")
	private String referenceId;
	@Column(name="unique_id")
	private String uniqueId = UUID.randomUUID().toString();
	@Column(name="created_at")
	private LocalDateTime createdAt = LocalDateTime.now();

	/**
	 * @deprecated Hibernate eyes only
	 */
	Purchase() {
	}

	public Purchase(User owner, List<PurchaseItem> items) {
		this.owner = owner;
		this.items = items;
	}

	public Long getId() {
		return id;
	}
	
	public List<PurchaseItem> getItems() {
		return items;
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
	
}
