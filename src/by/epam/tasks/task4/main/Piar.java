package by.epam.tasks.task4.main;

import java.util.concurrent.TimeUnit;

public class Piar {
	
	private Port port;

	public Piar(Port port) {
		this.port = port;
	}

	public void loadFromShip(Ship ship) throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		int remider = ship.getContainerCapacity() - port.getMaxContainerCapacity();
		int containersForShip = remider < 0 ? 0 : remider;
		int containersForPort = port.getContainerCapacity();
		containersForPort += remider < 0 ? ship.getContainerCapacity()
				: port.getMaxContainerCapacity() - port.getContainerCapacity();

		ship.setContainerCapacity(containersForShip);
		port.setContainerCapacity(containersForPort);
	}

	public void unloadForShip(Ship ship) throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		int remider = port.getContainerCapacity() - ship.getMaxContainerCapacity();
		int containersForShip = ship.getContainerCapacity();
		
		int containersForPort = remider < 0 ? 0 : remider;
		containersForShip += remider < 0 ? port.getContainerCapacity()
				: ship.getMaxContainerCapacity() - ship.getContainerCapacity();

		ship.setContainerCapacity(containersForShip);
		port.setContainerCapacity(containersForPort);
	}
}
