package com.test.Automation.sel.Digin.DbCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnection {
	public static void main(String args[]){
		try{
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			Connection con = DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost:1433/tbase", "sa", "Pass@2012");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from ttable");
			while(rs.next()){
				System.out.println(rs.getInt(1)+""+rs.getInt(1)+""+rs.getString(1)+""+rs.getString(1)+""+rs.getString(1));
				con.close();
			}
		}catch(Exception e){
				System.out.println(e);
			}
		}
	}


