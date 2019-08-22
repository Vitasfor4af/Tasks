package by.epam.tasks.task2.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import by.epam.tasks.task2.model.entity.Note;
import by.epam.tasks.task2.model.entity.Notepad;

public class Logic {
	
	public static void addNoteToList(Notepad notepad) {
		String line = "";
		String[] words;
		try (BufferedReader br = new BufferedReader(new FileReader("src/by/epam/tasks/task2/resources/noteList"))) {
			while ((line = br.readLine()) != null) {
				words = line.split("\\|");
				addNote(notepad, new Note(words[0], words[1], words[2], words[3]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addNote(Notepad notepad, Note note) {
		if(note != null) {			
			notepad.getNoteList().add(note);
		}
	}
	
	public static void removeNote(Notepad notepad, Note note) {
		if(note != null) {			
			notepad.getNoteList().remove(note);
		}
	}
	
	public static Note getNoteByName(Notepad notepad, String name) {
		Note note = null;
		if(name != null) {
			for (int i = 0; i < notepad.getNoteList().size(); i++) {
				if(notepad.getNoteList().get(i).getSubject().equals(name)) {
					note = notepad.getNoteList().get(i);
					break;
				}
			}
		}
		return note;
	}
}
