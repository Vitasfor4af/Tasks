package by.epam.tasks.task4.main;

public class Ship implements Runnable {
	private int containerCapacity;
	private Port port;
	private Thread thread;
	private int maxContainerCapacity;
	private static int shipNumber;
	private int number;

	public Ship(Port port, int containterCapacity, int maxContainerCapacity) {
		shipNumber++;
		number = shipNumber;
		this.port = port;
		this.containerCapacity = containterCapacity;
		this.maxContainerCapacity = maxContainerCapacity;
		if (maxContainerCapacity < 0) {
			throw new IllegalArgumentException("Invalid data");
		}
		thread = new Thread(this);
		thread.start();
	}

	public int getContainerCapacity() {
		return containerCapacity;
	}

	public void setContainerCapacity(int containerCapacity) {
		this.containerCapacity = containerCapacity;
	}

	public Port getPort() {
		return port;
	}

	public void setPort(Port port) {
		this.port = port;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public int getMaxContainerCapacity() {
		return maxContainerCapacity;
	}

	public void setMaxContainerCapacity(int maxContainerCapacity) {
		this.maxContainerCapacity = maxContainerCapacity;
	}

	public void loadContainer(int container) {
		if (containerCapacity + container <= maxContainerCapacity) {
			this.containerCapacity += container;
		}
	}

	public void unloadContainer(int container) {
		if (containerCapacity + container > 0) {
			this.containerCapacity -= container;
		}
	}

	@Override
	public void run() {
		int desire = (int) Math.round(Math.random() * 1);
		int portNumber = (int) Math.round(Math.random() * (port.piarList.length - 1));
		try {
			System.out.println(this + " is working...");
			if(desire == 0) {
				port.getPiarByInt(portNumber).loadFromShip(this);
			} else if(desire == 1) {
				port.getPiarByInt(portNumber).unloadForShip(this);
			}
			System.out.println(this + " is complete work.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Ship [containerCapacity=" + containerCapacity + ", number=" + number + "]";
	}
}
