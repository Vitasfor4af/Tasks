package by.epam.tasks.task3.client;

import static by.epam.tasks.task3.main.Main.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import by.epam.tasks.task3.entity.StudentFile;
import by.epam.tasks.task3.util.Reader;
import by.epam.tasks.task3.util.Writer;

public class Client implements Runnable {

	private Scanner scanner = new Scanner(System.in);

	private Socket socket;
	private ObjectOutputStream writer;
	private ObjectInputStream reader;

	private int access;
	private Thread thread;

	public Client(int access) {
		this.access = access;
		try {
			socket = new Socket(SERVER_IP, APP_PORT);
			System.out.println("Client start!");

			writer = new ObjectOutputStream(socket.getOutputStream());
			reader = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		thread = new Thread(this);
		thread.start();
	}

	public int getAccess() {
		return access;
	}

	@Override
	public void run() {
		while (!socket.isClosed()) {
			try {
				System.out.println("Maker a choose:" + "\n1.get student file by name."
						+ "\n2.change student file by name." + "\n3.add student file.\n4.Exit.");

				String choose = scanner.nextLine(); 
				switch (choose) {
				case "1": {
					Writer.writeObject(writer, GET_REQUEST);

					System.out.println("Input file name:");
					String fileName = scanner.nextLine();
					
					Writer.writeObject(writer, fileName);
					
					// get answer if file i found
					String answer = (String) Reader.readObject(reader);

					if (answer.equals(YES_FOUND_ANSWER)) {

						StudentFile studentFile = (StudentFile) Reader.readObject(reader);
						if (studentFile.getAccess() > access) {
							System.out.println("Access denied.");
						} else {
							System.out.println("File is:" + studentFile);
						}

					} else if (answer.equals(NO_FOUND_ANSWER)) {
						System.out.println("File is'nt found.");
					}

					break;
				}

				case "2": {
					Writer.writeObject(writer, CHANGE_REQUEST);

					System.out.println("Input file name:");
					String fileName = scanner.nextLine();

					Writer.writeObject(writer, fileName);

					// get answer if file i found
					String isFoundAnswer = (String) Reader.readObject(reader);

					if (isFoundAnswer.equals(YES_FOUND_ANSWER)) {

						StudentFile studentFile = (StudentFile) Reader.readObject(reader);
						if (studentFile.getAccess() > access) {
							System.out.println("Access denied.");
							Writer.writeObject(writer, NO_ACCESS_ANSWER);
						} else {
							Writer.writeObject(writer, YES_ACCESS_ANSWER);
							System.out.println("Input new first name:");
							studentFile.setFirstName(scanner.nextLine());

							System.out.println("Input new second name:");
							studentFile.setSecondName(scanner.nextLine());

							System.out.println("Input new patronymic:");
							studentFile.setPatronymic(scanner.nextLine());

							System.out.println("Input new average mark:");
							studentFile.setAverageMark(Integer.parseInt(scanner.nextLine()));

							Writer.writeObject(writer, studentFile);
						}

					} else if (isFoundAnswer.equals(NO_FOUND_ANSWER)) {
						System.out.println("File is'nt found.");
					}

					break;
				}

				case "3": {
					Writer.writeObject(writer, CREATE_REQUEST);

					StudentFile studentFile = new StudentFile();

					System.out.println("Input first name:");
					studentFile.setFirstName(scanner.nextLine());

					System.out.println("Input second name:");
					studentFile.setSecondName(scanner.nextLine());

					System.out.println("Input patronymic:");
					studentFile.setPatronymic(scanner.nextLine());

					System.out.println("Input average mark:");
					studentFile.setAverageMark(Integer.parseInt(scanner.nextLine()));

					System.out.println("Input file name:");
					studentFile.setFileName(scanner.nextLine());

					studentFile.setAccess(access);

					Writer.writeObject(writer, studentFile);
					break;
				}
				case "4": {
					Writer.writeObject(writer, EXIT_REQUEST);

					writer.close();
					reader.close();
					socket.close();
					System.out.println("Client was closed.");
					break;
				}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}

}
