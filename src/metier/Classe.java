package metier;

import java.io.Serializable;

public class Classe implements Serializable{
	
	private int identifiant;
	public int getIdentifiant() {
		return identifiant;
	}


	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}


	private String classeName;
	private String subjectName;
	
	
	public Classe() {
		super();
	}
	
	public Classe(int id) {
		super();
		this.identifiant=id;
	}


	public Classe(int identidiant,String classeName, String subjectName) {
		super();
		this.identifiant=identidiant;
		this.classeName = classeName;
		this.subjectName = subjectName;
	}
	
	public Classe(String classeName, String subjectName) {
		super();
		this.classeName = classeName;
		this.subjectName = subjectName;
	}


	public String getClasseName() {
		return classeName;
	}


	public void setClasseName(String classeName) {
		this.classeName = classeName;
	}


	public String getSubjectName() {
		return subjectName;
	}


	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	
}
