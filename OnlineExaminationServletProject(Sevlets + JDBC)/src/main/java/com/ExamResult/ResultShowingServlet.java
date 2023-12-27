package com.ExamResult;

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

@WebServlet("/ResultShowingServlet")

public class ResultShowingServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
   
	void homeButton (PrintWriter out) {
		
		out.println("<form action=\"home.html\">\r\n"
        		+ "    <button type=\"submit\">Return to home</button>\r\n"
        		+ "  </form>");
        
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
//		
		response.setContentType("text/html");
//		
		String resultRollno = request.getParameter("txtrollno");
		String resultPassword = request.getParameter("txtpassword") ;
		String url="jdbc:mysql://127.0.0.1:3306/online_examination";
		String uname="root";
		String pass="Hydrogen";
		
		Connection con = null ;
		PreparedStatement pre = null ;
		ResultSet res = null ;
	  
		PrintWriter out =  response.getWriter() ; 
 		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, uname, pass);
			
			PreparedStatement preAuth = null ;
			ResultSet resAuth = null ;
			
			preAuth = con.prepareStatement("Select * from online_examination.student_exam_login");
			resAuth  = preAuth.executeQuery() ; 
			
			int flagAuth = 0 ; 
			
			while(resAuth.next()) {
				String rollNoAuth = resAuth.getString("rollno");
				String passwordAuth = resAuth.getString("password") ; 
				
				if(rollNoAuth.equals(resultRollno)) {
					flagAuth  +=1 ;
					if(passwordAuth.equals(resultPassword)) {
						flagAuth +=1 ; 
					}
					break ; 
				}
			
				
			}
			
			if(flagAuth == 1) {
				out.println("You are student ,but please the enter correct password"); 
				homeButton(out) ; 
			}
			
			if(flagAuth == 0) {
				out.println("You are not a student of Our Organization accordding to you Roll Number..... please get out😡 ") ;
				homeButton(out) ; 
			}
			
			if(flagAuth == 2) {
				pre =  con.prepareStatement("Select * from online_examination.student_exam_mark where rollno = ? ");
				pre.setString(1,resultRollno) ;
				res = pre.executeQuery();
				int flagMark = 0 ;
				while(res.next()) {
					flagMark +=1 ; 
					String rono = res.getString("rollno");
				    String mark = res.getString("marks");
				    out.print("Hey "+rono +" Your mark is " + mark );
				    homeButton(out) ; 
				}		
				if(flagMark == 0) {
					out.println("Hey you have not written the examination , please the wirte the exam and come here tho check the result") ; 
					homeButton(out) ; 
				}
			}
 			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
