package com.senzo.qettal.checkout.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonInclude(Include.NON_NULL)
public class AddressDTO {
	
	@JsonProperty(required=true)
	private String street;
	@JsonProperty(required=true)
	private String number;
	@JsonProperty
	private String complement;
	@JsonProperty(required=true)
	private String district;
	@JsonProperty(required=true)
	private String city;
	@JsonProperty(required=true)
	private String state;
	@JsonProperty(required=true)
	private String country;
	@JsonProperty(value="zip_code", required=true)
	private String zipCode;

	/**
	 * @deprecated Jackson eyes only
	 */
	AddressDTO() {
	}
	
	private AddressDTO(String street, String number, String complement, String district, String city, String state, String country,
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

	public static AddressDTO from(Address address) {
		return new AddressDTO(address.getStreet(), address.getNumber(), address.getComplement(), address.getDistrict(), address.getCity(), address.getState(), address.getCountry(), address.getZipCode());
	}

	public Address toModel() {
		return new Address(street, number, complement, district, city, state, country, zipCode);
	}

}
