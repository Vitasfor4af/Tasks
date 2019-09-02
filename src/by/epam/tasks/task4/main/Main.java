package by.epam.tasks.task4.main;

/* Многопоточность. Порт . Корабли заходят в порт для
разгрузки/загрузки контейнеров. Число контейнеров, находящихся в текущий момент
в порту и на корабле, должно быть неотрицательным и превышающим заданную
грузоподъемность судна и вместимость порта. В порту работает несколько причалов.
У одного причала может стоять один корабль. Корабль может загружаться у причала
или разгружаться. */

public class Main {

	public static void main(String[] args) {
		
		Port port = new Port(4);
		System.out.println(port);
		Ship ship1 = new Ship(port, 2, 3);
		Ship ship2 = new Ship(port, 1, 1);
		Ship ship3 = new Ship(port, 2, 5);
		Ship ship4 = new Ship(port, 3, 1);
		System.out.println(ship1);
		System.out.println(ship2);
		System.out.println(ship3);
		System.out.println(ship4);
		try {
			ship1.getThread().join();
			ship2.getThread().join();
			ship3.getThread().join();
			ship4.getThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(ship1);
		System.out.println(ship2);
		System.out.println(ship3);
		System.out.println(ship4);
		System.out.println(port);
		

	}

}
