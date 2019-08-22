package by.epam.tasks.task1.dao.comparator;

import java.util.Comparator;

import by.epam.tasks.task1.model.entity.Book;

public class BookComparator implements Comparator<Book> {

	@Override
	public int compare(Book arg0, Book arg1) {
		int nameSort = arg0.getName().compareTo(arg1.getName());
		int authorSort = arg0.getAuthor().compareTo(arg1.getAuthor());

		int result = 0;
		if (nameSort == 0) {
			result = authorSort;
		} else {
			result = nameSort;
		}
		return result;
	}
}
