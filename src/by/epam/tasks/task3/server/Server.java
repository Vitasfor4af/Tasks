package by.epam.tasks.task3.server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import by.epam.tasks.task3.entity.StudentFile;
import by.epam.tasks.task3.util.Converter;
import by.epam.tasks.task3.util.Reader;
import by.epam.tasks.task3.util.Writer;

import static by.epam.tasks.task3.main.Main.*;

public class Server implements Runnable {

	List<StudentFile> studentFiles = new ArrayList<>();
	ServerSocket serverSocket;

	private Socket socket;
	private ObjectOutputStream writer;
	private ObjectInputStream reader;

	private Thread thread;

	public Server() {
		try {
			serverSocket = new ServerSocket(APP_PORT);
			initList();
			System.out.println("Server start working!");

			socket = serverSocket.accept();
			System.out.println("Server get connection!");
			writer = new ObjectOutputStream(socket.getOutputStream());
			reader = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		try {
			while (!socket.isClosed()) {
				String request = (String) Reader.readObject(reader);

//				System.out.println(request);
				switch (request) {
				case GET_REQUEST: {
					String fileName = (String) Reader.readObject(reader);
					int i = 0;
					for (; i < studentFiles.size(); i++) {
						if (studentFiles.get(i).getFileName().equals(fileName)) {
							Writer.writeObject(writer, YES_FOUND_ANSWER);
							Writer.writeObject(writer, studentFiles.get(i));
							break;
						}
					}

					if (i == studentFiles.size()) {
						Writer.writeObject(writer, NO_FOUND_ANSWER);
					}

					break;
				}

				case CHANGE_REQUEST: {
					String fileName = (String) Reader.readObject(reader);

					int i = 0;
					for (; i < studentFiles.size(); i++) {
						if (studentFiles.get(i).getFileName().equals(fileName)) {
							Writer.writeObject(writer, YES_FOUND_ANSWER);
							Writer.writeObject(writer, studentFiles.get(i));
							String answer = (String) Reader.readObject(reader);
							if (answer.equals(YES_ACCESS_ANSWER)) {
								studentFiles.set(i, (StudentFile) Reader.readObject(reader));
							} else if (answer.equals(NO_ACCESS_ANSWER)) {
								System.out.println("Not enough client accessibility.");
							}
							break;
						}
					}

					if (i == studentFiles.size()) {
						Writer.writeObject(writer, NO_FOUND_ANSWER);
					}

					break;
				}

				case CREATE_REQUEST: {
					studentFiles.add((StudentFile) Reader.readObject(reader));
					break;
				}

				case EXIT_REQUEST: {
					for (int i = 0; i < studentFiles.size(); i++) {
						Converter.objectToXml(studentFiles.get(i));
					}

					writer.close();
					reader.close();
					socket.close();
					serverSocket.close();
					System.out.println("Server was closed.");
					break;
				}

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void initList() {
		File folder = new File(FOLDER_PATH);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				studentFiles.add(Converter.xmlToObject(listOfFiles[i].getName().replaceAll(".xml", "")));
			}
		}
	}

}
