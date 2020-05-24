package model.dao.daoPostgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	static int feito = 0;
	
	public static String URL = "jdbc:postgresql:Livraaria";
	public static String USER = "postgres";
	public static String PASS = "root";
	
	static void fazConexao() {
			try(Connection c = DriverManager.getConnection(Conexao.URL,Conexao.USER,Conexao.PASS)){
				feito = 1;
			
		}catch(SQLException e) {
			e.printStackTrace();
			feito = 0;
		}
	}
	
	static Connection getConexao() throws SQLException{
		
		try{
			Connection c = DriverManager.getConnection(Conexao.URL,Conexao.USER,Conexao.PASS);


			
			return c;
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	
	}
	
	
}