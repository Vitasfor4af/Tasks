package by.epam.tasks.task1.model.entity;

import java.util.ArrayList;

public class Library {
	ArrayList<Book> bookList = new ArrayList<Book>();
	ArrayList<User> userList = new ArrayList<User>();

	public void addUser(User user) {
		if (user != null) {
			userList.add(user);
		}
	}

	public void removeUser(User user) {
		if (user != null) {
			userList.remove(user);
		}
	}

	public User getUserByLogin(String login) {
		User user = null;
		if (login != null) {
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getLogin().equals(login)) {
					user = userList.get(i);
					break;
				}
			}
		}
		return user;
	}

	public void addBook(Book book) {
		if (book != null) {
			bookList.add(book);
		}
	}

	public void removeBook(Book book) {
		if (book != null) {
			bookList.remove(book);
		}
	}

	public Book getBookByName(String name) {
		Book book = null;
		if (name != null) {
			for (int i = 0; i < bookList.size(); i++) {
				if (bookList.get(i).getName().equals(name)) {
					book = bookList.get(i);
					break;
				}
			}
		}
		return book;
	}

	public ArrayList<Book> getBookList() {
		return bookList;
	}

	public void setBookList(ArrayList<Book> bookList) {
		this.bookList = bookList;
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "Library [bookList=" + bookList + ", userList=" + userList + "]";
	}

}
