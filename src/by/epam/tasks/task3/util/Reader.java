package by.epam.tasks.task3.util;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Reader {

	public static Object readObject(ObjectInputStream reader) throws IOException {
		Object object = null;
		try {
			object = reader.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return object;
	}
}
