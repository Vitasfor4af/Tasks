package by.epam.tasks.task2.dao.comparator;

import java.util.Comparator;

import by.epam.tasks.task2.model.entity.Note;

public class NoteComparator implements Comparator<Note> {

	@Override
	public int compare(Note arg0, Note arg1) {
		int subjectSort = arg0.getSubject().compareTo(arg1.getSubject());
		int dateSort = arg0.getDate().compareTo(arg1.getDate());
		int emailSort = arg0.getEmail().compareTo(arg1.getEmail());
		int messageSort = arg0.getMessage().compareTo(arg1.getMessage());

		int result = 0;
		if (subjectSort == 0) {
			if (dateSort == 0) {
				if (emailSort == 0) {
					result = messageSort;
				} else {
					result = emailSort;
				}
			} else {
				result = dateSort;
			}
		} else {
			result = subjectSort;
		}
		return result;
	}
}
