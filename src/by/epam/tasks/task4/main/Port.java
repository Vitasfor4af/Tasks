package by.epam.tasks.task4.main;

public class Port {
	private final int DEFAULT = 5;
	private final int maxContainerCapacity = 5;
	private int containerCapacity;
	Piar[] piarList;

	{
		piarList = new Piar[DEFAULT];

		piarList[0] = new Piar(this);
		piarList[1] = new Piar(this);
		piarList[2] = new Piar(this);
		piarList[3] = new Piar(this);
		piarList[4] = new Piar(this);
	}

	public Port() {

	}
	
	public Port(int containerCapacity) {
		this.containerCapacity = containerCapacity;
	}

	public void loadContainer(int containers) {
		if (containers + containerCapacity > maxContainerCapacity) {
			this.containerCapacity += maxContainerCapacity - containerCapacity;
		} else {
			this.containerCapacity += containers;
		}
	}

	public void unloadContainer(int containers) {
		if (containerCapacity + containers > 0) {
			this.containerCapacity -= containers;
		}
	}

	public Piar getPairByNumber(int number) {
		return piarList[number];
	}

	public int getMaxContainerCapacity() {
		return maxContainerCapacity;
	}

	public int getContainerCapacity() {
		return containerCapacity;
	}

	public Piar[] getPiarList() {
		return piarList;
	}

	public void setPiarList(Piar[] piarList) {
		this.piarList = piarList;
	}
	
	public Piar getPiarByInt(int index) {
		return piarList[index];
	}

	public void setContainerCapacity(int containerCapacity) {
		this.containerCapacity = containerCapacity;
	}

	@Override
	public String toString() {
		return "Port [containerCapacity=" + containerCapacity + "]";
	}
}
