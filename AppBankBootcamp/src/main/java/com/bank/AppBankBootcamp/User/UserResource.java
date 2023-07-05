package com.bank.AppBankBootcamp.User;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bank.AppBankBootcamp.User.Account.Account;
import com.bank.AppBankBootcamp.User.Account.AccountDaoService;
import com.bank.AppBankBootcamp.User.Transaction.Transaction;
import com.bank.AppBankBootcamp.User.Transaction.TransactionDaoService;

@RestController
public class UserResource {
    
	private UserDaoService service;
    private AccountDaoService accountService;
    private TransactionDaoService transactionDaoService;

    public UserResource(UserDaoService service, AccountDaoService accountService,
			TransactionDaoService transactionDaoService) {
		super();
		this.service = service;
		this.accountService = accountService;
		this.transactionDaoService = transactionDaoService;
	}

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

//    @GetMapping("/users/{id}")
//    public EntityModel<User> retrieveAUser(@PathVariable int id) {
//        User user = service.findOne(id);
//        if (user == null)
//            return null;
//        // throw new UserNotFoundException("id: " + id);
//        EntityModel<User> entity = EntityModel.of(user);
//        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
//        entity.add(link.withRel("all-users"));
//        return entity;
//    }
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveAUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null)
            return null;
        EntityModel<User> entity = EntityModel.of(user);
        // Enlace al método GET de cuentas del usuario
        Link accountsLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).retrieveUserAccounts(id))
                .withRel("accounts");
        // Enlace al método GET de transacciones del usuario
        Link transactionsLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).retrieveUserTransactions(id))
                .withRel("transactions");
        entity.add(accountsLink, transactionsLink);
        // Enlace al método GET de todos los usuarios
        Link allUsersLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers())
                .withRel("all-users");
        entity.add(allUsersLink);
        return entity;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }

    // POST /users
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = service.save(user);
        // /users/(the id of the new user)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("/accounts/{userId}")
    public List<Account> retrieveUserAccounts(@PathVariable int userId) {
        return accountService.getAccountsByUser(userId);
    }
    @GetMapping("/transactions/{userId}")
    public List<Transaction> retrieveUserTransactions(@PathVariable int userId) {
        List<Account> userAccounts = accountService.getAccountsByUser(userId);

        List<Transaction> userTransactions = new ArrayList<>();

        for (Account account : userAccounts) {
            List<Transaction> accountTransactions = transactionDaoService.getTransactionsByAccount(account.getId());
            userTransactions.addAll(accountTransactions);
        }

        return userTransactions;
    }


	public AccountDaoService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountDaoService accountService) {
		this.accountService = accountService;
	}

	public TransactionDaoService getTransactionDaoService() {
		return transactionDaoService;
	}

	public void setTransactionDaoService(TransactionDaoService transactionDaoService) {
		this.transactionDaoService = transactionDaoService;
	}
}
