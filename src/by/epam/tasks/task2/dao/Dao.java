package by.epam.tasks.task2.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import by.epam.tasks.task2.dao.comparator.NoteComparator;
import by.epam.tasks.task2.model.entity.Note;

public class Dao {

	private static final String NOTE_FILE_PATH = "src/by/epam/tasks/task2/resources/noteList";

	public static void addNoteToFile(String subject, String date, String email, String message) {
		if (!isValidDate(date)) {
			throw new IllegalArgumentException("invalid date value");
		}
		if (!isValidEmail(email)) {
			throw new IllegalArgumentException("invalid email value");
		}
		try (FileWriter fileWriter = new FileWriter(NOTE_FILE_PATH, true)) {
			String newLine = subject + "|" + date + "|" + email + "|" + message + "\n";
			fileWriter.write(newLine);
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Note> searchBySubject(String subject) {
		ArrayList<Note> noteList = null;
		String[] values = null;
		try (Scanner scanner = new Scanner(new File(NOTE_FILE_PATH))) {
			scanner.useDelimiter("\\n");
			noteList = new ArrayList<Note>();
			while (scanner.hasNext()) {
				String line = scanner.next();

				values = line.split("\\|");

				if (values[0].equals(subject)) {
					Note note = new Note();

					note.setSubject(values[0]);
					note.setDate(values[1]);
					note.setEmail(values[2]);
					note.setMessage(values[3]);

					noteList.add(note);
				}
			}
			scanner.close();
			noteList.sort(new NoteComparator());
		} catch (FileNotFoundException | NumberFormatException e) {
			e.printStackTrace();
		}
		return noteList;
	}

	public static ArrayList<Note> searchByDate(String date) {
		ArrayList<Note> noteList = null;
		String[] values = null;
		try (Scanner scanner = new Scanner(new File(NOTE_FILE_PATH))) {
			scanner.useDelimiter("\\n");
			noteList = new ArrayList<Note>();
			while (scanner.hasNext()) {
				String line = scanner.next();

				values = line.split("\\|");

				if (values[1].equals(date)) {
					Note note = new Note();

					note.setSubject(values[0]);
					note.setDate(values[1]);
					note.setEmail(values[2]);
					note.setMessage(values[3]);

					noteList.add(note);
				}
			}
			scanner.close();
			noteList.sort(new NoteComparator());
		} catch (FileNotFoundException | NumberFormatException e) {
			e.printStackTrace();
		}
		return noteList;
	}

	public static ArrayList<Note> searchByEmail(String email) {
		ArrayList<Note> noteList = null;
		String[] values = null;
		try (Scanner scanner = new Scanner(new File(NOTE_FILE_PATH))) {
			scanner.useDelimiter("\\n");
			noteList = new ArrayList<Note>();
			while (scanner.hasNext()) {
				String line = scanner.next();

				values = line.split("\\|");

				if (values[2].equals(email)) {
					Note note = new Note();

					note.setSubject(values[0]);
					note.setDate(values[1]);
					note.setEmail(values[2]);
					note.setMessage(values[3]);

					noteList.add(note);
				}
			}
			scanner.close();
			noteList.sort(new NoteComparator());
		} catch (FileNotFoundException | NumberFormatException e) {
			e.printStackTrace();
		}
		return noteList;
	}

	public static ArrayList<Note> searchByMessage(String message) {
		ArrayList<Note> noteList = null;
		String[] values = null;
		try (Scanner scanner = new Scanner(new File(NOTE_FILE_PATH))) {
			scanner.useDelimiter("\\n");
			noteList = new ArrayList<Note>();
			while (scanner.hasNext()) {
				String line = scanner.next();

				values = line.split("\\|");

				if (values[3].contains(message)) {
					Note note = new Note();

					note.setSubject(values[0]);
					note.setDate(values[1]);
					note.setEmail(values[2]);
					note.setMessage(values[3]);

					noteList.add(note);
				}
			}
			scanner.close();
			noteList.sort(new NoteComparator());
		} catch (FileNotFoundException | NumberFormatException e) {
			e.printStackTrace();
		}
		return noteList;
	}

	private static boolean isValidDate(String date) {
		boolean result = true;
		if (date == null || !date.matches("[0-3]\\d.[0-1]\\d.\\d{4}")) {
			result = false;
		}
		return result;
	}

	private static boolean isValidEmail(String email) {
		boolean result = true;
		if (email == null || !email
				.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			result = false;
		}
		return result;
	}

}
