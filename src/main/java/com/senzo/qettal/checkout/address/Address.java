package com.senzo.qettal.checkout.address;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String street;
	private String number;
	private String complement;
	private String district;
	private String city;
	private String state;
	private String country;
	@Column(name="zip_code")
	private String zipCode;
	
	/**
	 * @deprecated Hibernate eyes only
	 */
	Address() {
	}

	public Address(String street, String number, String complement, String district, String city, String state, String country,
			String zipCode) {
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.district = district;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public String getNumber() {
		return number;
	}

	public String getComplement() {
		return complement;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getZipCode() {
		return zipCode;
	}
	
	public String getDistrict() {
		return district;
	}
	
}
