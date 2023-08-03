package com.bank.AppBankBootcamp.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 0;

	static {
		users.add(new User(1, "Adam", "White", "correo@solera.com", "contraseña"));
		users.add(new User(2, "Jose", "Perez", "joseperez@solera.com", "password"));
		users.add(new User(3, "Solera", "Solera", "solera@solera.com", "bootcamp2"));
		usersCount = 3;
	}
	public List<User> findAll() {
		return users;
	}
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId() == id;
		List<User> filteredList = users.stream().filter(predicate).toList();
		return (filteredList.size() != 1) ? null : filteredList.get(0);
	}
	public boolean deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId() == id; 
		return users.removeIf(predicate);
	}
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
}
