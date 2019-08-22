package by.epam.tasks.task3.entity;

import java.io.Serializable;

public class StudentFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -962082168668458881L;

	private String fileName;
	private String firstName;
	private String secondName;
	private String patronymic;
	private int averageMark;
	private int access;

	public StudentFile() {
		this.fileName = "Unnamed";
		this.firstName = "Unnamed";
		this.secondName = "Unnamed";
		this.patronymic = "Unnamed";
		this.averageMark = 1;
		this.access = 0;
	}

	public StudentFile(String fileName, String firstName, String secondName, String patronymic, int averageMark,
			int access) {
		super();
		this.fileName = fileName;
		this.firstName = firstName;
		this.secondName = secondName;
		this.patronymic = patronymic;
		this.averageMark = averageMark;
		this.access = access;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public int getAverageMark() {
		return averageMark;
	}

	public void setAverageMark(int averageMark) {
		this.averageMark = averageMark;
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + access;
		result = prime * result + averageMark;
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((patronymic == null) ? 0 : patronymic.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentFile other = (StudentFile) obj;
		if (access != other.access)
			return false;
		if (averageMark != other.averageMark)
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (patronymic == null) {
			if (other.patronymic != null)
				return false;
		} else if (!patronymic.equals(other.patronymic))
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentFile [fileName=" + fileName + ", firstName=" + firstName + ", secondName=" + secondName
				+ ", patronymic=" + patronymic + ", averageMark=" + averageMark + ", access=" + access + "]";
	}
}
