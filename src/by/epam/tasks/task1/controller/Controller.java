package by.epam.tasks.task1.controller;

import java.util.Scanner;

import by.epam.tasks.task1.dao.Dao;
import by.epam.tasks.task1.logic.Logic;
import by.epam.tasks.task1.model.entity.User;
import by.epam.tasks.task1.view.TextFormatter;

/* создать консольное приложение “Учет книг в домашней библиотеке”. */

public class Controller {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String key = "";
		String login;
		String password;
		String admin;
		User user = new User();
		Logic.addBookToList(user);
		while (!(key.equals("Y") || key.equals("y"))) {
			System.out.println("-------------------------------------------");
			System.out.println("###########################################");
			System.out.println("-------------------------------------------");
			System.out.println("\t\t***MENU***");
			System.out.println("\t1 - Log In");
			System.out.println("\t2 - Sign Up");
			System.out.println("\tc - exit");
			switch (key = scanner.nextLine()) {
			case "1":
				System.out.println("Input login");
				login = scanner.nextLine();
				System.out.println("Input password");
				password = scanner.nextLine();

				if (Dao.searchUser(login, password) == null) {
					throw new NullPointerException("User with this login and password was not found");
				}

				if (Dao.searchUser(login, password).isAdmin()) {
					adminMenu(scanner, user);
				} else {
					userMenu(scanner, user);
				}
				break;
			case "2":
				System.out.println("Input login");
				login = scanner.nextLine();
				System.out.println("Input password");
				password = scanner.nextLine();
				System.out.println("Input access level (admin(true) or user(false))");
				admin = scanner.nextLine();

				while (Dao.searchUserByLogin(login)) {
					System.out.println("User with this login already exists, try again.");
					System.out.println("Input login");
					login = scanner.nextLine();
					System.out.println("Input password");
					password = scanner.nextLine();
					System.out.println("Input access level (admin(true) or user(false))");
					admin = scanner.nextLine();
				}

				Dao.addUserToFile(login, password, Boolean.parseBoolean(admin));
				System.out.println("Do you wish to exit from program[Y/N]?");
				key = scanner.nextLine();
				if (key.equals("Y") || key.equals("y")) {
					scanner.close();
				} else if (!(key.equals("N") || key.equals("n"))) {
					doDefault(key, scanner);
				}
				break;
			case "c":
			case "C":
				System.out.println("Exit from program");
				key = "Y";
				if (key.equals("Y")) {
					scanner.close();
				}
				break;
			default:
				doDefault(key, scanner);
				break;
			}
		}
	}

	public static void search(Scanner scanner) {
		String key = "";
		String bookName;
		String bookAuthor;
		while (!(key.equals("Y") || key.equals("y"))) {
			System.out.println("-------------------------------------------");
			System.out.println("###########################################");
			System.out.println("-------------------------------------------");
			System.out.println("\t\t***SEARCH MENU***");
			System.out.println("\t1 - search by name");
			System.out.println("\t2 - search by author");
			System.out.println("\tc - back");
			switch (key = scanner.nextLine()) {
			case "1":
				System.out.println("Input book name");
				bookName = scanner.nextLine();

				System.out.println(TextFormatter.formatBookList(Dao.searchBookByName(bookName)));
				System.out.println("Do you want to go back [Y/N]?");
				key = scanner.nextLine();
				if (key.equals("Y") || key.equals("y")) {
					return;
				} else if (!(key.equals("N") || key.equals("n"))) {
					doDefault(key, scanner);
				}
				break;
			case "2":
				System.out.println("Input book author");
				bookAuthor = scanner.nextLine();

				System.out.println(TextFormatter.formatBookList(Dao.searchBookByAuthor(bookAuthor)));
				System.out.println("Do you want to go back [Y/N]?");
				key = scanner.nextLine();
				if (key.equals("Y") || key.equals("y")) {
					return;
				} else if (!(key.equals("N") || key.equals("n"))) {
					doDefault(key, scanner);
				}
				break;
			case "c":
			case "C":
				return;
			default:
				doDefault(key, scanner);
				break;
			}
		}
	}

	private static void userMenu(Scanner scanner, User user) {
		String key = "";
		user.getBookList().clear();
		Logic.addBookToList(user);
		while (!(key.equals("Y") || key.equals("y"))) {
			System.out.println("-------------------------------------------");
			System.out.println("###########################################");
			System.out.println("-------------------------------------------");
			System.out.println("\t\t***USER MENU***");
			System.out.println("\t1 - get book list");
			System.out.println("\t2 - search book into file");
			System.out.println("\tc - back");
			switch (key = scanner.nextLine()) {
			case "1":
				System.out.println(TextFormatter.formatBookList(user.getBookList()));
				System.out.println("Do you want to go back [Y/N]?");
				key = scanner.nextLine();
				if (key.equals("Y") || key.equals("y")) {
					return;
				} else if (!(key.equals("N") || key.equals("n"))) {
					doDefault(key, scanner);
				}
				break;
			case "2":
				search(scanner);
				break;
			case "c":
			case "C":
				return;
			default:
				doDefault(key, scanner);
				break;
			}
		}
	}

	private static void adminMenu(Scanner scanner, User user) {
		String key = "";
		String bookName;
		String bookAuthor;
		String electronic;
		while (!(key.equals("Y") || key.equals("y"))) {
			System.out.println("-------------------------------------------");
			System.out.println("###########################################");
			System.out.println("-------------------------------------------");
			System.out.println("\t\t***ADMIN MENU***");
			System.out.println("\t1 - add book");
			System.out.println("\t2 - remove book");
			System.out.println("\tc - back");
			switch (key = scanner.nextLine()) {
			case "1":
				System.out.println("Input book name");
				bookName = scanner.nextLine();
				System.out.println("Input book author");
				bookAuthor = scanner.nextLine();
				System.out.println("Input book type(electronic (true) or paper (false))");
				electronic = scanner.nextLine();

				Dao.addBookToFile(bookName, bookAuthor, Boolean.parseBoolean(electronic));
				System.out.println("Do you want to go back [Y/N]?");
				key = scanner.nextLine();
				if (key.equals("Y") || key.equals("y")) {
					return;
				} else if (!(key.equals("N") || key.equals("n"))) {
					doDefault(key, scanner);
				}
				break;
			case "2":
				System.out.println("Input book name");
				bookName = scanner.nextLine();
				System.out.println("Input book author");
				bookAuthor = scanner.nextLine();

				if (Dao.searchBook(bookName, bookAuthor)) {
					Dao.removeBook(user, bookName, bookAuthor);
					System.out.println("Book with the name \"" + bookName + "\" and the author of \"" + bookAuthor
							+ "\" has been deleted.");
				} else {
					System.out.println("Book with the same name and author was not found.");
				}
				System.out.println("Do you want to go back [Y/N]?");
				key = scanner.nextLine();
				if (key.equals("Y") || key.equals("y")) {
					return;
				} else if (!(key.equals("N") || key.equals("n"))) {
					doDefault(key, scanner);
				}
				break;
			case "c":
			case "C":
				return;
			default:
				doDefault(key, scanner);
				break;
			}
		}
	}

	private static void doDefault(String key, Scanner scanner) {
		System.out.println("Unsupported key was pressed");
		System.out.println("Do you want to go back [Y]?");
		key = scanner.nextLine();
		if (key.equals("Y") || key.equals("y")) {
			return;
		} else if (!(key.equals("Y") || key.equals("y"))) {
			doDefault(key, scanner);
		}
	}
}
