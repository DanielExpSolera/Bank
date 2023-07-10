package com.bank.AppBankBootcamp.User;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController()
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
 
    @PostMapping("/users/{userId}/accounts")
    public ResponseEntity<Account> createAccountForUser(@PathVariable int userId, @RequestBody Account account) {
        User user = service.findOne(userId);
        if (user == null) {
            // Devolver una respuesta de error o lanzar una excepción si el usuario no existe
            return ResponseEntity.notFound().build();
        }
        // Asignar el usuario a la cuenta
        account.setUserId(userId);
        // Guardar la cuenta en el AccountDaoService
        Account savedAccount = accountService.save(account);
        // Construir la URI de la ubicación de la cuenta creada
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAccount.getId())
                .toUri();
        // Devolver una respuesta con el código 201 (Created) y la ubicación de la cuenta creada
        return ResponseEntity.created(location).body(savedAccount);
    }
    
    @PostMapping("/users/{userId}/transactions")
    public ResponseEntity<Transaction> createTransactionForUser(@PathVariable int userId, @RequestBody Transaction transaction) {
        User user = service.findOne(userId);
        if (user == null) {
            // Devolver una respuesta de error o lanzar una excepción si el usuario no existe
            return ResponseEntity.notFound().build();
        }
        // Asignar el usuario a la transacción
        transaction.setUserId(userId);
        // Guardar la transacción en el TransactionDaoService
        Transaction savedTransaction = transactionDaoService.save(transaction);
        // Construir la URI de la ubicación de la transacción creada
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTransaction.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedTransaction);
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
	

	@GetMapping("/users/{userId}/transactions")
	public CollectionModel<Transaction> retrieveUserTransactions(@PathVariable int userId) {
	    List<Transaction> userTransactions =  transactionDaoService.getTransactionsByUser(userId);
	    CollectionModel<Transaction> collectionModel = CollectionModel.of(userTransactions);
	    // Agregar enlaces al recurso
	    collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).retrieveUserTransactions(userId)).withSelfRel());
	    collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).retrieveAUser(userId)).withRel("user"));

	    return collectionModel;
	}

	@GetMapping("/users/{userId}/accounts")
	public CollectionModel<Account> retrieveUserAccounts(@PathVariable int userId) {
	    List<Account> userAccounts =  accountService.getAccountsByUser(userId);
	    CollectionModel<Account> collectionModel = CollectionModel.of(userAccounts);
	    // Agregar enlaces al recurso
	    collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).retrieveUserTransactions(userId)).withSelfRel());
	    collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).retrieveAUser(userId)).withRel("user"));

	    return collectionModel;
	}

}
