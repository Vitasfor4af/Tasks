package by.epam.tasks.task2.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import by.epam.tasks.task2.dao.Dao;
import by.epam.tasks.task2.model.entity.Note;
import by.epam.tasks.task2.model.entity.Notepad;

public class Logic {

	public static void addNoteToList(Notepad notepad) {
		String line = "";
		String[] words;
		try (BufferedReader br = new BufferedReader(new FileReader(Dao.NOTE_FILE_PATH))) {
			while ((line = br.readLine()) != null) {
				words = line.split("\\|");
				notepad.addNote(new Note(words[0], words[1], words[2], words[3]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
