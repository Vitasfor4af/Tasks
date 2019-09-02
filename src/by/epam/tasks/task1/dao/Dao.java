package by.epam.tasks.task1.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import by.epam.tasks.task1.dao.comparator.BookComparator;
import by.epam.tasks.task1.model.entity.Book;
import by.epam.tasks.task1.model.entity.Library;
import by.epam.tasks.task1.model.entity.User;

public class Dao {

	public static final String USER_FILE_PATH = "src/by/epam/tasks/task1/resources/userList";
	public static final String BOOK_FILE_PATH = "src/by/epam/tasks/task1/resources/bookList";

	public static void addUserToFile(String login, String password, boolean admin) {
		if (!isValidLogin(login)) {
			throw new IllegalArgumentException("Invalid login");
		}
		if (!isValidPassword(password)) {
			throw new IllegalArgumentException("Invalid password");
		}
		try (FileWriter fileWriter = new FileWriter(USER_FILE_PATH, true)) {
			String newLine = login + "|" + password + "|" + admin + "\n";
			fileWriter.write(newLine);
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addBookToFile(String name, String author, boolean electronic) {
		if (!isValidAuthor(author)) {
			throw new IllegalArgumentException("Invalid login");
		}
		try (FileWriter fileWriter = new FileWriter(BOOK_FILE_PATH, true)) {
			String newLine = name + "|" + author + "|" + electronic + "\n";
			fileWriter.write(newLine);
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean searchUserByLogin(String login) {
		String[] values = null;
		try (Scanner scanner = new Scanner(new File(USER_FILE_PATH))) {
			scanner.useDelimiter("\\n");

			while (scanner.hasNext()) {
				String line = scanner.next();

				values = line.split("\\|");

				if (values[0].equals(login)) {
					return true;
				}
			}
		} catch (FileNotFoundException | NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean searchBook(String name, String author) {
		String[] values = null;
		try (Scanner scanner = new Scanner(new File(BOOK_FILE_PATH))) {
			scanner.useDelimiter("\\n");

			while (scanner.hasNext()) {
				String line = scanner.next();

				values = line.split("\\|");

				if (values[0].equals(name) && values[1].equals(author)) {
					return true;
				}
			}
		} catch (FileNotFoundException | NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static User searchUser(String login, String password) {
		String[] values = null;
		try (Scanner scanner = new Scanner(new File(USER_FILE_PATH))) {
			scanner.useDelimiter("\\n");

			while (scanner.hasNext()) {
				String line = scanner.next();
				values = line.split("\\|");

				if (values[0].equals(login) && values[1].equals(password)) {
					User user = new User();
					user.setLogin(values[0]);
					user.setPassword(values[1]);
					user.setAdmin(Boolean.parseBoolean(values[2]));

					return user;
				}
			}
		} catch (FileNotFoundException | NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Book> searchBookByName(String name) {
		ArrayList<Book> bookList = null;
		String[] values = null;
		try (Scanner scanner = new Scanner(new File(BOOK_FILE_PATH));) {
			scanner.useDelimiter("\\n");
			bookList = new ArrayList<Book>();
			while (scanner.hasNext()) {
				String line = scanner.next();

				values = line.split("\\|");

				if (values[0].equals(name)) {
					Book book = new Book();

					book.setName(values[0]);
					book.setAuthor(values[1]);
					book.setElectronic(Boolean.parseBoolean(values[2]));

					bookList.add(book);
				}
			}
			scanner.close();
			bookList.sort(new BookComparator());
		} catch (FileNotFoundException | NumberFormatException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	public static ArrayList<Book> searchBookByAuthor(String author) {
		ArrayList<Book> bookList = null;
		String[] values = null;
		try (Scanner scanner = new Scanner(new File(BOOK_FILE_PATH))) {
			scanner.useDelimiter("\\n");
			bookList = new ArrayList<Book>();
			while (scanner.hasNext()) {
				String line = scanner.next();

				values = line.split("\\|");

				if (values[1].equals(author)) {
					Book book = new Book();

					book.setName(values[0]);
					book.setAuthor(values[1]);
					book.setElectronic(Boolean.parseBoolean(values[2]));

					bookList.add(book);
				}
			}
			scanner.close();
			bookList.sort(new BookComparator());
		} catch (FileNotFoundException | NumberFormatException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	public static void removeBook(Library library, String name, String author) {
		String newLine = "";
		File file = new File(BOOK_FILE_PATH);
		file.delete();
		try (FileWriter fileWriter = new FileWriter(file, true)) {
			for (int i = 0; i < library.getBookList().size(); i++) {
				newLine = library.getBookList().get(i).getName() + "|" + library.getBookList().get(i).getAuthor() + "|"
						+ library.getBookList().get(i).isElectronic() + "\n";
				if (!(library.getBookList().get(i).getName().equals(name)
						&& library.getBookList().get(i).getAuthor().equals(author))) {
					fileWriter.write(newLine);
					fileWriter.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean isValidLogin(String login) {
		if (login.matches("^[a-zA-Z0-9._-]{3,}$")) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isValidPassword(String password) {
		if (password.matches("\\w+")) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isValidAuthor(String author) {
		if (author.matches("\\p{IsLatin}+")) {
			return true;
		} else {
			return false;
		}
	}
}
