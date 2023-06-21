package service_classe;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import Connection.Connect;
import metier.Classe;

public class ClasseServiceImpl extends UnicastRemoteObject implements ClasseRemote{

	private static final long serialVersionUID = 1L;
	private String requete;
	private PreparedStatement ps;
	private Resultset rs;
	private Connect connection= new Connect();

	public ClasseServiceImpl() throws RemoteException {
		super();
		
	}

	@Override
	public void createCls(Classe classe) throws RemoteException {
		try {
			requete="insert into classe(classeName, SubjectName) values(?,?);";
			ps = connection.connecter(requete);
			ps.setString(1, classe.getClasseName());
			ps.setString(2, classe.getSubjectName());
		    ps.executeUpdate();
			ps.close();
			connection.closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void editCls(Classe classe) throws RemoteException {
		
		try {
			requete="UPDATE classe set classeName = '" +classe.getClasseName()+"', subjectName='"+classe.getSubjectName()+"' where id="+classe.getIdentifiant()+";"; 	
			ps = connection.connecter(requete);
			ps.executeUpdate();
			ps.close();
			connection.closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCls(Classe classe) throws RemoteException {
		try {
			requete="delete from classe where id =" +classe.getIdentifiant()+";"; 	
			ps = connection.connecter(requete);
			ps.executeUpdate();
			ps.close();
			connection.closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Classe> listCls() throws RemoteException {
		ArrayList<Classe> classes = new ArrayList<Classe>();
		String classeName=null;
		String subjectName=null;
		int id =0;
		try {
			requete="select * from classe;"; 	
			ps = connection.connecter(requete);
			rs = (Resultset) ps.executeQuery();
			while (((ResultSet) rs).next()) {
				 classeName= ((ResultSet) rs).getString("classeName");
				 subjectName=((ResultSet) rs).getString("subjectName");
				 id =((ResultSet) rs).getInt("id");
				Classe classe = new Classe(id, classeName, subjectName);
				classes.add(classe);
			}
			ps.close();
			connection.closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classes;
	}

}
