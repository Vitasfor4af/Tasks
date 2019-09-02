package by.epam.tasks.task2.controller;

import java.util.Scanner;

import by.epam.tasks.task2.dao.Dao;
import by.epam.tasks.task2.logic.Logic;
import by.epam.tasks.task2.model.entity.Notepad;
import by.epam.tasks.task2.view.TextFormatter;

/* Блокнот. Разработать консольное приложение, работающее с Заметками в
   Блокноте. Каждая Заметка это: Заметка (тема, дата создания, e-mail,
   сообщение). */

public class Controller {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String key = "";
		String subject;
		String date;
		String email;
		String message;
		Notepad notepad = new Notepad();
		notepad.getNoteList().clear();
		Logic.addNoteToList(notepad);
		while (!(key.equals("Y") || key.equals("y"))) {
			System.out.println("-------------------------------------------");
			System.out.println("###########################################");
			System.out.println("-------------------------------------------");
			System.out.println("\t\t***MENU***");
			System.out.println("\t1 - add note to file");
			System.out.println("\t2 - search note into file");
			System.out.println("\tc - exit");
			switch (key = scanner.nextLine()) {
			case "1":
				System.out.println("Input subject");
				subject = scanner.nextLine();
				System.out.println("Input date");
				date = scanner.nextLine();
				System.out.println("Input email");
				email = scanner.nextLine();
				System.out.println("Input message");
				message = scanner.nextLine();

				Dao.addNoteToFile(subject, date, email, message);
				System.out.println("Do you wish to exit from program[Y/N]?");
				key = scanner.nextLine();
				if (key.equals("Y") || key.equals("y")) {
					scanner.close();
				} else if (!(key.equals("N") || key.equals("n"))) {
					doDefault(key, scanner);
				}
				break;
			case "2":
				search(scanner);
				break;
			case "c":
			case "C":
				System.out.println("Exit from program");
				key = "Y";
				scanner.close();
				break;
			default:
				doDefault(key, scanner);
				break;
			}
		}
	}

	private static void search(Scanner scanner) {
		String key = "";
		String subject;
		String date;
		String email;
		String message;
		while (!(key.equals("Y") || key.equals("y"))) {
			System.out.println("-------------------------------------------");
			System.out.println("###########################################");
			System.out.println("-------------------------------------------");
			System.out.println("\t\t***SEARCH MENU***");
			System.out.println("\t1 - search by subject");
			System.out.println("\t2 - search by date");
			System.out.println("\t3 - search by email");
			System.out.println("\t4 - search by message");
			System.out.println("\tc - back");
			switch (key = scanner.nextLine()) {
			case "1":
				System.out.println("Input the subject of note");
				subject = scanner.nextLine();

				System.out.println(TextFormatter.format(Dao.searchBySubject(subject)));
				System.out.println("Do you want to go back [Y/N]?");
				key = scanner.nextLine();
				if (key.equals("Y") || key.equals("y")) {
					return;
				} else if (!(key.equals("N") || key.equals("n"))) {
					doDefault(key, scanner);
				}
				break;
			case "2":
				System.out.println("Input the date of note");
				date = scanner.nextLine();

				System.out.println(TextFormatter.format(Dao.searchByDate(date)));
				System.out.println("Do you want to go back [Y/N]?");
				key = scanner.nextLine();
				if (key.equals("Y") || key.equals("y")) {
					return;
				} else if (!(key.equals("N") || key.equals("n"))) {
					doDefault(key, scanner);
				}
				break;
			case "3":
				System.out.println("Input the email of note");
				email = scanner.nextLine();

				System.out.println(TextFormatter.format(Dao.searchByEmail(email)));
				System.out.println("Do you want to go back [Y/N]?");
				key = scanner.nextLine();
				if (key.equals("Y") || key.equals("y")) {
					return;
				} else if (!(key.equals("N") || key.equals("n"))) {
					doDefault(key, scanner);
				}
				break;
			case "4":
				System.out.println("Input the message of note");
				message = scanner.nextLine();

				System.out.println(TextFormatter.format(Dao.searchByMessage(message)));
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
		System.out.println("Unsupported key was pressed\npress Enter to return");
		scanner.nextLine();
		key = "Y";
		if (key.equals("Y") || key.equals("y")) {
			return;
		} else if (!(key.equals("Y") || key.equals("y"))) {
			doDefault(key, scanner);
		}
	}
}
