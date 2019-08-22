package by.epam.tasks.task1.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import by.epam.tasks.task1.model.entity.Book;
import by.epam.tasks.task1.model.entity.User;

public class Logic {

	public static void addUserToList() {
		String line = "";
		String[] words;
		try (BufferedReader br = new BufferedReader(new FileReader("src/by/epam/tasks/task1/resources/userList"))) {
			while ((line = br.readLine()) != null) {
				words = line.split("\\|");
				addUser(new User(words[0], words[1], Boolean.parseBoolean(words[2])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addUser(User user) {
		if (user != null) {
			user.getUserList().add(user);
		}
	}

	public static void removeUser(User user) {
		if (user != null) {
			user.getUserList().remove(user);
		}
	}

	public static User getUserByLogin(User user, String login) {
		User userCompare = null;
		if (login != null) {
			for (int i = 0; i < user.getUserList().size(); i++) {
				if (user.getUserList().get(i).getLogin().equals(login)) {
					userCompare = user.getUserList().get(i);
					break;
				}
			}
		}
		return userCompare;
	}

	public static void addBookToList(User user) {
		String line = "";
		String[] words;
		try (BufferedReader br = new BufferedReader(new FileReader("src/by/epam/tasks/task1/resources/bookList"))) {
			while ((line = br.readLine()) != null) {
				words = line.split("\\|");
				addBook(user, new Book(words[0], words[1], Boolean.parseBoolean(words[2])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addBook(User user, Book book) {
		if (book != null) {
			user.getBookList().add(book);
		}
	}

	public static void removeBook(User user, Book book) {
		if (book != null) {
			user.getBookList().remove(book);
		}
	}

	public static Book getBookByName(User user, String name) {
		Book book = null;
		if (name != null) {
			for (int i = 0; i < user.getBookList().size(); i++) {
				if (user.getBookList().get(i).getName().equals(name)) {
					book = user.getBookList().get(i);
					break;
				}
			}
		}
		return book;
	}
}
