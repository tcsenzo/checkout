package com.senzo.qettal.checkout.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDTO {

	@JsonProperty("credit_card_hash")
	private String creditCardHash;
	@JsonProperty("full_name")
	private String fullName;
	@JsonProperty("birth_date")
	private String birthDate;
	@JsonProperty("phone_area_code")
	private String phoneAreaCode;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("cpf")
	private String cpf;
	@JsonProperty("purchase_id")
	private Long purchaseId;
	
	public String getCreditCardHash() {
		return creditCardHash;
	}

	public String getFullName() {
		return fullName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public String getPhoneAreaCode() {
		return phoneAreaCode;
	}

	public String getPhone() {
		return phone;
	}

	public String getCpf() {
		return cpf;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

}
