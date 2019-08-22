package by.epam.tasks.task3.util;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class Writer {

	public static void writeObject(ObjectOutputStream writer, Object object) {
		try {
			writer.writeObject(object);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
