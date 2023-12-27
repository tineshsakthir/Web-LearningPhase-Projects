package com.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class demoDatabaseConnectivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String url="jdbc:mysql://127.0.0.1:3306/online_examination";
		String uname="root";
		String pass="Hydrogen";
		
		Connection con = null ;
		PreparedStatement pre = null ;
		ResultSet res = null ;
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, uname, pass);
			pre = con.prepareStatement("select * from student_exam_registration");
			res = pre.executeQuery();
			while(res.next()) {
				String rollno = res.getString("rollno").trim() ;
				String password = res.getString("password").trim() ; 
				
				out.print(rollno + password) ; 
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	

}