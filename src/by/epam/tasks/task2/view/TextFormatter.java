package by.epam.tasks.task2.view;

import java.util.List;

import by.epam.tasks.task2.model.entity.Note;

public class TextFormatter {

	public static String format(List<Note> noteList) {
		String str = "";
		for (int i = 0; i < noteList.size(); i++) {
			str += noteList.get(i) + "\n";
		}
		return str;
	}
	
}
