package com.bank.AppBankBootcamp.User.Account;

import java.util.List;

import com.bank.AppBankBootcamp.User.Transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Account {
	
	public Account(int initialFunds, String accNumber, int userId, int id) {
		super();
		this.accNumber = accNumber;
		this.initialFunds = initialFunds;
		this.userId = userId;
		this.id = id;
		this.type = "savings";
	}
	@NotBlank
	@JsonProperty("funds")
	public int initialFunds;
	@NotNull
	public String accNumber;
	public int id;
	@NotBlank
	@JsonProperty("userId")
	public int userId;
	public String type;
	public List<Transaction> transactions;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public String getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInitialFunds() {
		return initialFunds;
	}
	public void setInitialFunds(int initialFunds) {
		this.initialFunds = initialFunds;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Account [initialFunds=" + initialFunds + ", accNumber=" + accNumber + ", id=" + id + ", userId="
				+ userId + "]";
	}

}
