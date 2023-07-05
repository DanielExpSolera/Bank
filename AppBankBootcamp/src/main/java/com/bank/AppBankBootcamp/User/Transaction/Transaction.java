package com.bank.AppBankBootcamp.User.Transaction;

public class Transaction {

	public int amount;
	public String destAccNumber;
	public String isuedTo;
	public int userId;
	public int accountId;
	public int id;
	public Transaction(int amount, String destAccNumber, String isuedTo, int userId, int accountId) {
		super();
		this.amount = amount;
		this.destAccNumber = destAccNumber;
		this.isuedTo = isuedTo;
		this.accountId = accountId;
		this.userId = userId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getIsuedTo() {
		return isuedTo;
	}
	public void setIsuedTo(String isuedTo) {
		this.isuedTo = isuedTo;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int id) {
		this.accountId = id;
	}
	public String getDestAccNumber() {
		return destAccNumber;
	}
	public void setDestAccNumber(String destAccNumber) {
		this.destAccNumber = destAccNumber;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", destAccNumber=" + destAccNumber + ", isuedTo=" + isuedTo
				+ ", userId=" + userId + ", accountId=" + accountId + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
