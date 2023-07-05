package com.bank.AppBankBootcamp.User.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class TransactionDaoService {
	public static List<Transaction> transactions = new ArrayList<>();
	public static int transactionsCount = 0;
	static {
		transactions.add(new Transaction(1000, "4444444222222", "Federico García", 1));
		transactions.add(new Transaction(10000, "1234567891234", "", 2));
		transactionsCount = 2;
	}
	
	public List<Transaction> findAll() {
		return transactions;
	}
	public Transaction findOne(int id) {
		Predicate<? super Transaction> predicate = transaction -> transaction.getId() == id; 
		return transactions.stream().filter(predicate).findFirst().orElse(null);
	}
	public void deleteById(int id) {
		Predicate<? super Transaction> predicate = transaction -> transaction.getId() == id; 
		transactions.removeIf(predicate);
	}
	public Transaction save(Transaction newTransaction) {
		newTransaction.setId(++transactionsCount);
		transactions.add(newTransaction);
		return newTransaction;
	}
	public List<Transaction> getTransactionsByAccount(int accountId) {
	    List<Transaction> accountTransactions = new ArrayList<>();
	    for (Transaction transaction : transactions) {
	        if (transaction.getId() == accountId) {
	            accountTransactions.add(transaction);
	        }
	    }
	    return accountTransactions;
	}

}
