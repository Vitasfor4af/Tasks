package by.epam.tasks.task2.model.entity;

import java.util.ArrayList;

public class Notepad {
	ArrayList<Note> noteList = new ArrayList<Note>();

	public void addNote(Note note) {
		if (note != null) {
			noteList.add(note);
		}
	}

	public void removeNote(Note note) {
		if (note != null) {
			noteList.remove(note);
		}
	}

	public Note getNoteByName(String name) {
		Note note = null;
		if (name != null) {
			for (int i = 0; i < noteList.size(); i++) {
				if (noteList.get(i).getSubject().equals(name)) {
					note = noteList.get(i);
					break;
				}
			}
		}
		return note;
	}

	public ArrayList<Note> getNoteList() {
		return noteList;
	}

	public void setNoteList(ArrayList<Note> noteList) {
		this.noteList = noteList;
	}

	@Override
	public String toString() {
		return "Notepad [noteList=" + noteList + "]";
	}
}
