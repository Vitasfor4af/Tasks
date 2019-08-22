package by.epam.tasks.task3.main;

import java.util.Scanner;

import by.epam.tasks.task3.client.Client;
import by.epam.tasks.task3.server.Server;

public class Main {
	public static final String GET_REQUEST = "get";
	public static final String CHANGE_REQUEST = "change";
	public static final String CREATE_REQUEST = "create";
	public static final String EXIT_REQUEST = "exit";
	public static final String YES_FOUND_ANSWER = "yes";
	public static final String NO_FOUND_ANSWER = "no";
	public static final String YES_ACCESS_ANSWER = "access";
	public static final String NO_ACCESS_ANSWER = "noaccess";

	public static final int APP_PORT = 3333;
	public static final String SERVER_IP = "localhost";
	public static final String FILE_PATH = "src/by/epam/tasks/task3/resources/";
	public static final String FOLDER_PATH = "src/by/epam/tasks/task3/resources";
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Make a choose:\n1.Log in as client.\n2.Server up.");
		String choose = scanner.nextLine();
		switch (choose) {

		case "1": {
			System.out.println("Input client accessibility:");
			Client client = new Client(scanner.nextInt());
			break;
		}

		case "2": {
			Server server = new Server();
			break;
		}

		default: {
			System.out.println("Wrong choosing.");
		}

		}
	}
}
