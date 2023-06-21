package serveur;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import metier.Student;
import service_classe.*;


public class ServeurRMI {

	public static void main(String[] args) {
		
		try {
			LocateRegistry.createRegistry(1099);
			ClasseServiceImpl classe = new ClasseServiceImpl();
			Naming.rebind("rmi://localhost:1099/Classe", classe);
			
			StudentServiceImpl student = new StudentServiceImpl();
			Naming.rebind("rmi://localhost:1099/Student", student);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
