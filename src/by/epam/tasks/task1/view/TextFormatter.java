package by.epam.tasks.task1.view;

import java.util.List;

import by.epam.tasks.task1.model.entity.Book;

public class TextFormatter {
	
	public static String formatBookList(List<Book> bookList) {
		String str = "";
		for (int i = 0; i < bookList.size(); i++) {
			str += bookList.get(i) + "\n";
		}
		return str;
	}
}
