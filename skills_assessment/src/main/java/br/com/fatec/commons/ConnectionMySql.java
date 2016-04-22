package br.com.fatec.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionMySql {
// UTILZIZAR OPENSHIFT
//	private static String user="adminX4h1tLm";
//  private static String password="CTRx7SYKUx1G";
//  private static String ip="127.4.220.2:3306/SkillsAssessment?useSSL=false";
	private static String user="root";
    private static String password="inacio";
    private static String ip="localhost:3306/SkillsAssessment?useSSL=false";
    private static String driver="com.mysql.jdbc.Driver";
        
    public ConnectionMySql(){};
   
	public Connection getConection(){
    	System.out.println(">>criando uma conexão. . .");
    	try{   		
    		Class.forName(driver);
            return DriverManager.getConnection("jdbc:mysql://"+ip+"",user, password);
    	}catch(ClassNotFoundException ex){
    		Logger.getLogger(ConnectionMySql.class.getName()).log(Level.SEVERE,null, ex);
    	}catch (SQLException e) {
            throw new RuntimeException(e);
    	}
		return null;          	
    }
    
    //SELECT USA SE executeQuery
        
    	
    //INSERT, UPDATE E DELETE
    public boolean executeSql(PreparedStatement ps) throws SQLException {
        int value = ps.executeUpdate();
        return value == 0 ?  false : true;
    }
        
	
}
