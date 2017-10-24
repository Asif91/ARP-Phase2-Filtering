package arp;

/*
 * This is a connection class for mysql interaction
 * @author Asif Ali
 * */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class connection {

//  Database credentials localhost
	/*
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/personality";
	   
	
	   static final String USER = "root";
	   static final String PASS = "";
	 */  	 
	
	

	//  Database credentials live demo	   
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://cos80001.cxoswcz1r4nq.ap-southeast-2.rds.amazonaws.com:3306/PersonalityAnalyserDemo";
	   
	
	   static final String USER = "root";
	   static final String PASS = "password";
	
	/*
	//  Database credentials real data	   
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://cos80001.cxoswcz1r4nq.ap-southeast-2.rds.amazonaws.com:3306/PersonalityAnalyser";
	   
	//  Database credentials
	   static final String USER = "root";
	   static final String PASS = "password";
	 
	  */ 
	   static Connection conn = null;
	   
	   public static void closeConnection()
	   {
		   try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   
	   public static void openConnection() 
	   {
		   try {
			Class.forName("com.mysql.jdbc.Driver");
			  //STEP 3: Open a connection
	//	      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		  
		   
		   } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
  
	   }
	   
	   
	   public static ResultSet insertSqlQuery (String sql)
		{
		    boolean found = false ;

			ResultSet rs = null ;
			String lastid ; 
			try {
				Statement stmt = conn.createStatement() ;
				stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
				rs = stmt.getGeneratedKeys();

				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			
			return rs ;
			
		}
	   


	   public static ResultSet selectSqlQuery (String sql)
		{
		  // connection.openConnection();
			ResultSet rs = null ;
			 
			try {
				Statement stmt = conn.createStatement() ;
				rs = stmt.executeQuery(sql);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			
			return rs ;
			
		}



	   public static ResultSet selectSqlQuery2 (String sql2)
		{
		 //  connection.openConnection();
			ResultSet rss = null ;
			 
			try {
				Statement stmt2 = conn.createStatement() ;
				rss = stmt2.executeQuery(sql2);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			
			return rss ;
			
		}

	   
	   
	   public static int deleteSqlQuery (String sql)
		{
		    int delete = 0 ; 
		//	connection.openConnection();
				try {
				Statement stmt = conn.createStatement() ;
				delete = stmt.executeUpdate(sql);
			

				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			
			return delete ;
			
		}


	   public static boolean updateSqlQuery (String sql)
		{
		  // connection.openConnection();
			ResultSet rs = null ;
			 
			try {
				Statement stmt = conn.createStatement() ;
			   stmt.executeUpdate(sql) ;
			   
				return true ;
			   
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

				return false ;
			}
				
		//	connection.closeConnection();			
		}
	   

	   //Statement stmt = null ;
	   
	      //STEP 2: Register JDBC driver
	     	   

}
