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
		transactions.add(new Transaction(1000, "4444444222222", "Federico García", 1, 1));
		transactions.add(new Transaction(10000, "1234567891234", "", 2, 2));
		transactions.add(new Transaction(1000, "0000000000", "Federico García", 3, 3));
		transactions.add(new Transaction(-11500, "1212121212", "Pedro Expósito", 3, 3));
		transactionsCount = 4;
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
	public List<Transaction> getTransactionsByUser(int userId) {
	    List<Transaction> accountTransactions = new ArrayList<>();
	    for (Transaction transaction : transactions) {
	        if (transaction.getUserId() == userId) {
	            accountTransactions.add(transaction);
	        }
	    }
	    return accountTransactions;
	}
	public List<Transaction> getTransactionsByAccount(int accountId) {
	    List<Transaction> accountTransactions = new ArrayList<>();
	    for (Transaction transaction : transactions) {
	        if (transaction.getAccountId() == accountId) {
	            accountTransactions.add(transaction);
	        }
	    }
	    return accountTransactions;
	}

}
