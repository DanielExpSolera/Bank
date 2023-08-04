package com.bank.AppBankBootcamp.User;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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


    // POST /users
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = service.save(user);
        // /users/(the id of the new user)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedUser);
    }

    @PostMapping("/users/accounts")
    public ResponseEntity<Object> createAccountForUser(@RequestBody Account account) {
        User user = service.findOne(account.getUserId());
        if (user == null) {
            // Devolver una respuesta de error o lanzar una excepción si el usuario no
            // existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + account.getUserId() + " not found.");
        }
        // Guardar la cuenta en el AccountDaoService
        Account savedAccount = accountService.save(account);
        // Construir la URI de la ubicación de la cuenta creada
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAccount.getId())
                .toUri();
        // Devolver una respuesta con el código 201 (Created) y la ubicación de la
        // cuenta creada
        return ResponseEntity.created(location).body(savedAccount);
    }

    @PostMapping("/users/transactions")
    public ResponseEntity<Object> createTransactionForUser(@RequestBody Transaction transaction) {
        User user = service.findOne(transaction.getUserId());
        Account account = accountService.findOne(transaction.getAccountId());
        if (user == null || account == null || account.getUserId() != user.getId()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("It's impossible to create a transaction for the User with ID " + transaction.getUserId() + " for an account with an ID " + transaction.getAccountId());
        }
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
    public ResponseEntity<Object> retrieveUserTransactions(@PathVariable int userId) {
        List<Transaction> userTransactions = transactionDaoService.getTransactionsByUser(userId);
        if (userTransactions.isEmpty()) {
            String notFoundMessage = "User with ID " + userId + " transactions not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        }
        CollectionModel<Transaction> collectionModel = CollectionModel.of(userTransactions);
        // Agregar enlaces al recurso
        collectionModel.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).retrieveUserAccounts(userId)).withSelfRel());
        collectionModel.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).retrieveAUser(userId)).withRel("user"));

        return ResponseEntity.ok(collectionModel);
    }
    @GetMapping("/users/{userId}/transaction")
    public ResponseEntity<Object> retrieveUserTransactionById(@PathVariable int userId, @RequestParam int transactionId) {
        Transaction requestTransaction = transactionDaoService.findOne(transactionId);
        if (requestTransaction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + userId + " don't have a transaction with the ID " + transactionId);        }
        return ResponseEntity.ok(requestTransaction);
    }

    @GetMapping("/users/{userId}/accounts")
    public ResponseEntity<Object> retrieveUserAccounts(@PathVariable int userId) {
        List<Account> userAccounts = accountService.getAccountsByUser(userId);
        if (userAccounts.isEmpty()) {
            String notFoundMessage = "User with ID " + userId + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        }
        List<Object> accountObjects = new ArrayList<>();
        for (Account account : userAccounts) {
            List<Transaction> transactions = transactionDaoService.getTransactionsByAccount(account.getId());
            if (!transactions.isEmpty()) {
                account.setTransactions(transactions);
            }
            accountObjects.add(account);
        }
        CollectionModel<Object> collectionModel = CollectionModel.of(accountObjects);
        // Agregar enlaces al recurso
        collectionModel.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).retrieveUserTransactions(userId)).withSelfRel());
        collectionModel.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).retrieveAUser(userId)).withRel("user"));

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/users/{userId}/accounts/{accountId}")
    public ResponseEntity<Object> retrieveUserAccount(@PathVariable int userId, @PathVariable int accountId) {
        Account account = accountService.getAccountById(accountId, userId);
        User user = service.findOne(userId);
        String notFoundMessage = new String(); 
        if (user == null) {
            notFoundMessage = "User with ID " + userId + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        }
        if (account == null) {
            notFoundMessage = "Account with id " + accountId + " not found with the userId " + userId;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        }
        List<Transaction> transactions = transactionDaoService.getTransactionsByAccount(account.getId());
        if (!transactions.isEmpty()) {
                account.setTransactions(transactions);
        }
        CollectionModel<Object> collectionModel = (CollectionModel<Object>) CollectionModel.of(account);
        // Agregar enlaces al recurso
        collectionModel.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).retrieveUserTransactions(userId)).withSelfRel());
        collectionModel.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).retrieveAUser(userId)).withRel("user"));

        return ResponseEntity.ok(collectionModel);
    }
    @DeleteMapping("/users")
    public ResponseEntity<Object> deleteUserById(@RequestParam(name="id", required = true) String userId){
        User user = service.findOne(Integer.valueOf(userId));
        String notFoundMessage = new String();
        if (user == null)
        {
            notFoundMessage = "User with ID " + userId + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        }
        //return ResponseEntity.ok().build();
        service.deleteById(user.getId());
        String successMessage = "User with ID " + userId + " deleted successfully.";
        return ResponseEntity.status(HttpStatus.OK).body(successMessage);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        User user = service.findOne(Integer.valueOf(id));

        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " not found.");

        service.deleteById(user.getId());

        String successMessage = "User with ID " + id + " deleted successfully.";
        return ResponseEntity.status(HttpStatus.OK).body(successMessage);
    }
    @DeleteMapping("/users/accounts")
    public ResponseEntity<Object> deleteUserAccountByAccountId(@RequestParam(name="accountId", required = true) String accountId)
    {
        Account account = accountService.findOne(Integer.valueOf(accountId));
        String notFoundMessage = new String(); 
        if (account == null)
        {
            notFoundMessage = "User account with ID " + accountId+ " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        }
        accountService.deleteById(Integer.valueOf(accountId));
        String successMessage = "User account with ID " + accountId + " deleted successfully.";
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successMessage);
    }
    @DeleteMapping("/users/transactions")
    public ResponseEntity<Object> deleteUserTransactionById(@RequestParam(name="transactionId", required = true) String transactionId)
    {
        Transaction transaction = transactionDaoService.findOne(Integer.valueOf(transactionId));
        if (transaction == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User transaction with ID " + transactionId + " not found.");

        accountService.deleteById(Integer.valueOf(transactionId));

        return ResponseEntity.status(HttpStatus.OK).body("User transaction with ID " + transactionId + " deleted successfully.");
    }
}
