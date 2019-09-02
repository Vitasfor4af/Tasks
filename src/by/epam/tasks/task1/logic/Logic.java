package by.epam.tasks.task1.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import by.epam.tasks.task1.dao.Dao;
import by.epam.tasks.task1.model.entity.Book;
import by.epam.tasks.task1.model.entity.Library;
import by.epam.tasks.task1.model.entity.User;

public class Logic {

	public static void addUserToList(Library library) {
		String line = "";
		String[] words;
		try (BufferedReader br = new BufferedReader(new FileReader(Dao.USER_FILE_PATH))) {
			while ((line = br.readLine()) != null) {
				words = line.split("\\|");
				library.addUser(new User(words[0], words[1], Boolean.parseBoolean(words[2])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addBookToList(Library library) {
		String line = "";
		String[] words;
		try (BufferedReader br = new BufferedReader(new FileReader(Dao.BOOK_FILE_PATH))) {
			while ((line = br.readLine()) != null) {
				words = line.split("\\|");
				library.addBook(new Book(words[0], words[1], Boolean.parseBoolean(words[2])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
