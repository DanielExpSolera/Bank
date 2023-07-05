package com.bank.AppBankBootcamp.User.Transaction;

public class Transaction {
	
	public int amount;
	public String destAccNumber;
	public String isuedTo;
	public int id;
	public Transaction(int amount, String destAccNumber, String isuedTo, int id) {
		super();
		this.amount = amount;
		this.destAccNumber = destAccNumber;
		this.isuedTo = isuedTo;
		this.id = id;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDestAccNumber() {
		return destAccNumber;
	}
	public void setDestAccNumber(String destAccNumber) {
		this.destAccNumber = destAccNumber;
	}
	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", destAccNumber=" + destAccNumber
				+ ", isuedTo=" + isuedTo + ", id=" + id + "]";
	}

}
