package service_classe;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import metier.Classe;

public interface ClasseRemote extends Remote {
	
	public void createCls(Classe classe) throws RemoteException;
	
	public void editCls(Classe classe) throws RemoteException;
	
	public void deleteCls(Classe classe) throws RemoteException;
	
	public List<Classe> listCls() throws RemoteException;
}
