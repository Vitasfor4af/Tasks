package by.epam.tasks.task2.model.entity;

import java.util.ArrayList;

public class Notepad {
	ArrayList<Note> noteList = new ArrayList<Note>();

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
