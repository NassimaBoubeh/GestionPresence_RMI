package service_classe;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import metier.Student;

public interface StudentRemote  extends Remote{
	

	public void createStudent(Student student) throws RemoteException;
	
	public void editStudent(Student student) throws RemoteException;
	
	public void deleteStudent(Student student) throws RemoteException;
	
	public List<Student> listStudent() throws RemoteException;

}
