package metier;

import java.io.Serializable;

public class Student implements Serializable{
	
	private int identifiant;
	private String name;
	private String status;
	
	public Student(int identifiant, String name, String status) {
		super();
		this.identifiant = identifiant;
		this.name = name;
		this.status = status;
	}
	
	public Student(String name, String status) {
		super();
		this.name = name;
		this.status = status;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
