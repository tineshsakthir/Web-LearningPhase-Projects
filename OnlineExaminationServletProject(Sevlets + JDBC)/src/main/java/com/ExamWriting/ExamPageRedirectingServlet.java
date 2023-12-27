package com.ExamWriting;

import jakarta.servlet.RequestDispatcher;
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

@WebServlet("/ExamPageRedirectingServlet")

public class ExamPageRedirectingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String testrollno = request.getParameter("txtrollno").trim() ; 
		String testpassword = request.getParameter("txtpassword").trim() ; 
		
		
		String url="jdbc:mysql://127.0.0.1:3306/online_examination";
		String uname="root";
		String pass="Hydrogen";
		
		Connection con = null ;
		PreparedStatement pre = null ;
		PreparedStatement pre1 = null ;
		ResultSet res = null ;
		ResultSet res1 = null ;
		PrintWriter out = response.getWriter();
		int flagToWriteTest = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, uname, pass);
			pre1 = con.prepareStatement("Select * from student_exam_mark");
			res1 = pre1.executeQuery();
			while(res1.next()){
				String rollno = res1.getString("rollno").trim() ;
				if(testrollno.equals(rollno)) {
					flagToWriteTest = 1 ;
					out.println("<!DOCTYPE html>\r\n"
							+ "<html lang=\"en\">\r\n"
							+ "\r\n"
							+ "<head>\r\n"
							+ "  <meta charset=\"UTF-8\">\r\n"
							+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
							+ "  <title>Document</title>\r\n"
							+ "</head>\r\n"
							+ "\r\n"
							+ "<body>\r\n"
							+ "  <h4>Sorry , you were already written the exam , please contact your db admin to alter the table ,  <br>  which enables you to\r\n"
							+ "    rewrite the examination .......</h4>\r\n"
							+ "  <form action=\"home.html\">\r\n"
							+ "    <button type=\"submit\">Return to Home </button>\r\n"
							+ "\r\n"
							+ "  </form>\r\n"
							+ "</body>\r\n"
							+ "\r\n"
							+ "</html>");
				}
			}
			
			while(flagToWriteTest == 0) {
				pre = con.prepareStatement("select * from student_exam_login");
				
				res = pre.executeQuery();
				int flag = 0 ; 
				while(res.next()) {
					String rollno = res.getString("rollno").trim() ;
					String password = res.getString("password").trim() ; 
					
					if(testrollno.equals(rollno) && testpassword.equals(password)) {
						Cookie cookie1 = new Cookie("curUser", rollno); 
						response.addCookie(cookie1);
						Cookie cookie2 = new Cookie("curUserPassword" , password);
						response.addCookie(cookie2);					
						flag = 1 ;
						break ; 
					}

					
	//testing				out.print(testpassword + " " +  password + " " +testrollno +" " + rollno + "\n"); 
				}
				
				if (flag == 0) {
				    // Display an error message for unsuccessful login.
				    out.println("You have not entered the correct username and password...");
				} else {
					out.println(testrollno + " " +testpassword) ;
				    // Forward the request to the JSP page when the form is submitted.
				    RequestDispatcher dispatcher = request.getRequestDispatcher("/examWriting.jsp");
				    dispatcher.forward(request, response);
				}
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
