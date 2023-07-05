package com.bank.AppBankBootcamp.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bank.AppBankBootcamp.User.Account.AccountDaoService;
import com.bank.AppBankBootcamp.User.Transaction.TransactionDaoService;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 2;

	static {
		users.add(new User(1, "Adam", "White", "correo@solera.com", "contraseña"));
		users.add(new User(2, "Jose", "Perez", "joseperez@solera.com", "password"));
	}
	public List<User> findAll() {
		return users;
	}
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId() == id; 
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId() == id; 
		users.removeIf(predicate);
	}
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
}
