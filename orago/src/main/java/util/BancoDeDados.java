package util;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class BancoDeDados {
	
	public transient Connection connection = null;
	public transient Statement statement = null;
	public transient ResultSet resultset = null;
	
	public void conectar() {
	    
		String servidor = "localhost";
        //String servidor = "//179.109.81.128:44359/orago";
        String url = "jdbc:mysql:" + servidor;
        String usuario = "admin";
        //String usuario = "henrique.debona";
        String senha = "admin";
        //String senha = "h3Nr!1995#@%";
        String driver = "com.mysql.jdbc.Driver";
    	try {
			Class.forName(driver);
        	this.setConnection(DriverManager.getConnection(url, usuario, senha));
        	this.setStatement(this.connection.createStatement());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public boolean estaConectado() {
		if(this.getConnection() != null) {
			return true;
		} else {
			return false;
		}
	}

	public ResultSet getResultset() {
		return resultset;
	}

	public void setResultset(ResultSet resultset) {
		this.resultset = resultset;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
    
}