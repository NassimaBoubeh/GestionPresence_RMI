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
import metier.Student;

public class StudentServiceImpl extends UnicastRemoteObject implements StudentRemote{
	private static final long serialVersionUID = 1L;
	private String requete;
	private PreparedStatement ps;
	private Resultset rs;
	private Connect connection= new Connect();
	
	public StudentServiceImpl() throws RemoteException {
		super();
		
	}

	@Override
	public void createStudent(Student student) throws RemoteException {
		try {
			requete="insert into student(name, status) values(?,?);";
			ps = connection.connecter(requete);
			ps.setString(1, student.getName());
			ps.setString(2, student.getStatus());
		    ps.executeUpdate();
			ps.close();
			connection.closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void editStudent(Student student) throws RemoteException {
		try {
			requete="UPDATE student set name = '" +student.getName()+"', status='"+student.getStatus()+"' where id="+student.getIdentifiant()+";"; 	
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
	public void deleteStudent(Student student) throws RemoteException {
		try {
			requete="delete from student where id =" +student.getIdentifiant()+";"; 	
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
	public List<Student> listStudent() throws RemoteException {
		ArrayList<Student> students = new ArrayList<Student>();
		String name=null;
		String status=null;
		int id =0;
		try {
			requete="select * from student;"; 	
			ps = connection.connecter(requete);
			rs = (Resultset) ps.executeQuery();
			while (((ResultSet) rs).next()) {
				 name= ((ResultSet) rs).getString("name");
				 status=((ResultSet) rs).getString("status");
				 id =((ResultSet) rs).getInt("id");
				 Student student = new Student(id, name, status);
				 students.add(student);
			}
			ps.close();
			connection.closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}

	


	

}
