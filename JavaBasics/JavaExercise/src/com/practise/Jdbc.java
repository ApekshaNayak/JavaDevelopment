package com.practise;
/**
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * @author M1016987
 *
 */
public class Jdbc {
	static Connection conn= null;
	static void connectEstab(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static Connection getConnection(){
		Connection conn=null;
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","Mysql","Mysql");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	static void update(Integer Int,String str0, String str1, String str2, Date dat0, Date dat1){
		final String str="insert into empregsys values(?,?,?,?,?,?)";
		
		try {
			connectEstab();
			conn=getConnection();
			PreparedStatement pstmt=conn.prepareStatement(str);
			pstmt.setInt(1,Int);
			pstmt.setString(2, str0);
			pstmt.setString(3, str1);
			pstmt.setString(4, str2);
			pstmt.setDate(5, (java.sql.Date) dat0);
			pstmt.setDate(6,(java.sql.Date) dat1);
			
			pstmt.executeUpdate();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void retrive(String name0){
		String name="Select *from empregsys where empName="+"'"+name0+"'";

		ResultSet rs=null;
		connectEstab();
		conn=getConnection();
		try {
			Statement stmt=conn.createStatement();
			rs=stmt.executeQuery(name);
			while(rs.next()){
				System.out.println("Employee ID: "+rs.getInt(1));
				System.out.println("Employee Name: "+rs.getString(2));
				System.out.println("Education qualification: "+rs.getString(3));
				System.out.println("Place of Birth: "+rs.getString(4));
				System.out.println("Date of Birth: "+rs.getDate(5));
				System.out.println("Date of joining: "+rs.getDate(6));
			}
				
							
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
