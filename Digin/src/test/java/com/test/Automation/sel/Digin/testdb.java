package com.test.Automation.sel.Digin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class testdb {
	public static void main(String args[]){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost:1433/test", "sa", "kbs");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from ttable");
			while(rs.next()){
				System.out.println(rs.getInt(1)+""+rs.getInt(1)+""+rs.getString(1)+""+rs.getString(1)+""+rs.getString(1));
				con.close();
			}
		}catch(Exception e){
				System.out.println(e);
			}
		
		/*try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:ttable");
			Statement st= con.createStatement();
			st.executeUpdate("insert into ttable values(5,'harish')");
			con.commit();
			ResultSet rs= st.executeQuery("select * from ttable");
			while(rs.next()){
				System.out.println(rs.getInt(1)+""+rs.getString(1));
				con.close();
			}
		}catch(Exception e){
				System.out.println(e);
			}
		}*/
	}
}


