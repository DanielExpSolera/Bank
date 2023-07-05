package com.bank.AppBankBootcamp.User.Account;
import com.bank.AppBankBootcamp.User.Transaction.TransactionDaoService;

public class Account {
	
	public Account(int initialFunds, String accNumber, TransactionDaoService transactions, int id) {
		super();
		this.accNumber = accNumber;
		this.transactions = transactions;
		this.id = id;
		this.initialFunds = initialFunds;
	}
	public int initialFunds;
	public String accNumber;
	public TransactionDaoService transactions;
	public int transactionsCount = 0;
	public int id;
	
	public String getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}
	public TransactionDaoService getTransactions() {
		return transactions;
	}
	public void setTransactions(TransactionDaoService transactions) {
		this.transactions = transactions;
	}
	public int getTransactionsCount() {
		return transactionsCount;
	}
	public void setTransactionsCount(int transactionsCount) {
		this.transactionsCount = transactionsCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Account [accNumber=" + accNumber + ", transactions=" + transactions
				+ ", transactionsCount=" + transactionsCount + ", id=" + id + "]";
	}
	public int getInitialFunds() {
		return initialFunds;
	}
	public void setInitialFunds(int initialFunds) {
		this.initialFunds = initialFunds;
	}

}
