package com.bank.AppBankBootcamp.User.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bank.AppBankBootcamp.User.Transaction.TransactionDaoService;
@Component
public class AccountDaoService {
	@Autowired
	private TransactionDaoService transactionService;
	
	public static List<Account> accounts = new ArrayList<>();
	public static int accountsCount = 0;
	static {
		//there shouldn't initialize false values, in the controller should created it with the data on the dataBase, in this case we don't have dataBase
		//so that's why we just create some example accounts and transactions for all the users.
		accounts.add(new Account(1000, "1234567891234", 1, 1));
		accounts.add(new Account(-1000, "9876543219876", 2, 2));
		accounts.add(new Account(-1000, "9876543219876", 3, 3));
		accounts.add(new Account(-1000, "8888888888888", 3, 4));
		accountsCount = 4;
	}
	
	public List<Account> findAll() {
		return accounts;
	}
	public Account findOne(int id) {
		Predicate<? super Account> predicate = Account -> Account.getId() == id; 
		return accounts.stream().filter(predicate).findFirst().orElse(null);
	}
	public void deleteById(int accountId) {
		Predicate<? super Account> predicate = Account -> Account.getId() == accountId; 
		accounts.removeIf(predicate);
	}
	public Account save(Account newAccount) {
		newAccount.setId(++accountsCount);
		accounts.add(newAccount);
		return newAccount;
	}
	public TransactionDaoService getTransactionService() {
		return transactionService;
	}
	public void setTransactionService(TransactionDaoService transactionService) {
		this.transactionService = transactionService;
	}
	public List<Account> getAccountsByUser(int userId) {
	    List<Account> userAccounts = new ArrayList<>();
	    for (Account account : accounts) {
	        if (account.getUserId() == userId) {
	            userAccounts.add(account);
	        }
	    }

	    return userAccounts;
	}
	public List<Account> getAccountsById(int Id, int userId) {
	    List<Account> userAccounts = new ArrayList<>();
	    for (Account account : accounts) {
	        if (account.getId() == Id && account.getUserId() == userId) {
	            userAccounts.add(account);
	        }
	    }
	    return userAccounts;
	}
	public Account getAccountById(int Id, int userId) {
		Account userAccounts = null;
		for (Account account : accounts) {
			if (account.getId() == Id && account.getUserId() == userId) {
				userAccounts = account;
			}
		}
		return userAccounts;
	}
}