package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Connect {
	private String url="jdbc:mysql://localhost:3306/studentpresence?serverTimezone=UTC";
    private String userName="root";
    private String password="";
    private Connection con;
    
    public PreparedStatement connecter(String requete) {
    	PreparedStatement ps=null;
    	try {
    		con = (Connection) DriverManager.getConnection(url, userName, password);
			ps = ((java.sql.Connection) con).prepareStatement(requete);
			System.out.println("la connexion est établie");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ps;
    }
    
    public void closeCon() {
 
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
    }
}
