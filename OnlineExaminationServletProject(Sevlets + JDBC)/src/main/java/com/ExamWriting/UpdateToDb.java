package com.ExamWriting;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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

@WebServlet("/UpdateToDb")

public class UpdateToDb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet	(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		
		Cookie [] cookies = request.getCookies();
		PrintWriter out  =  response.getWriter();
		int flag = 0 ;
		String userNameToPutInTheDb = null ;
		String userMarkToPutInTheDb = null ; 
		
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("curUser") ) {
				userNameToPutInTheDb = cookie.getValue()  ; 
			}
		}
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("curUserMark") ) {
				userMarkToPutInTheDb = cookie.getValue()  ; 
			}
		}
		
		String url="jdbc:mysql://127.0.0.1:3306/online_examination";
		String uname="root";
		String pass="Hydrogen";
		
		Connection con = null ;
		PreparedStatement pre = null ;
		ResultSet res = null ;
		
	   out.println("<html><head><title>Exam Result</title></head><body>");

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, uname, pass);
			pre = con.prepareStatement("INSERT INTO online_examination.student_exam_mark (rollno, marks) VALUES ( ?, ?);");
			pre.setString(1,userNameToPutInTheDb);
			pre.setString(2,userMarkToPutInTheDb);
			int rowsUpdated = pre.executeUpdate();

			if (rowsUpdated > 0) {
			    // The update was successful
			    out.println("Update successful. " + rowsUpdated + " row(s) updated.");
			} else {
			    // The update was not successful
			    out.println("Update failed.");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("<form action=\"home.html\">\r\n"
        		+ "    <button type=\"submit\">Return to home</button>\r\n"
        		+ "  </form>");
        

        out.println("</body></html>");
        		
	}

}
