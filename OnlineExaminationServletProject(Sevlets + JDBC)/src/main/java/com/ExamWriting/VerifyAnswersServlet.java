package com.ExamWriting;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/VerifyAnswersServlet")

public class VerifyAnswersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        int totalCurrectAnswer = 0 ; 

        // Retrieve the selected answers from the request.
        String answer1 = request.getParameter("q1");
        String answer2 = request.getParameter("q2");
        String answer3 = request.getParameter("q3");
        String answer4 = request.getParameter("q4");
        String answer5 = request.getParameter("q5");
        String answer6 = request.getParameter("q6");

       

        // Define the correct answers.
        String correctAnswer1 = "b";
        String correctAnswer2 = "b";
        String correctAnswer3 = "c";
        String correctAnswer4 = "a";
        String correctAnswer5 = "b";
        String correctAnswer6 = "a";

        // Check the answers and provide feedback.
        out.println("<html><head><title>Exam Result</title></head><body>");
        out.println("<h1>Exam Result</h1>");

        if (correctAnswer1.equals(answer1)) {
            out.println("<p>Question 1: Correct!</p>");
            totalCurrectAnswer++;
        } else {
            out.println("<p>Question 1: Incorrect. Correct answer: b</p>");
        }

        if (correctAnswer2.equals(answer2)) {
            out.println("<p>Question 2: Correct!</p>");
            totalCurrectAnswer++;
        } else {
            out.println("<p>Question 2: Incorrect. Correct answer: b</p>");
        }

        if (correctAnswer3.equals(answer3)) {
            out.println("<p>Question 3: Correct!</p>");
            totalCurrectAnswer++;
        } else {
            out.println("<p>Question 3: Incorrect. Correct answer: c</p>");
        }
        if (correctAnswer4.equals(answer4)) {
            out.println("<p>Question 4: Correct!</p>");
            totalCurrectAnswer++;
        } else {
            out.println("<p>Question 4: Incorrect. Correct answer: a</p>");
        }

        if (correctAnswer5.equals(answer5)) {
            out.println("<p>Question 5: Correct!</p>");
            totalCurrectAnswer++;
        } else {
            out.println("<p>Question 5: Incorrect. Correct answer: b</p>");
        }

        if (correctAnswer6.equals(answer6)) {
            out.println("<p>Question 6: Correct!</p>");
            totalCurrectAnswer++;
        } else {
            out.println("<p>Question 6: Incorrect. Correct answer: a</p>");
        }
        
        out.println(" <b>you have got</b> " + totalCurrectAnswer + " <b>out of 6</b>");
        
        Cookie userMark = new Cookie("curUserMark" ,  Integer.toString(totalCurrectAnswer)) ; 
        response.addCookie(userMark);
        
        out.println("<form action=\"UpdateToDb\">\r\n"
        		+ "    <button type=\"submit\">Update to DB</button>\r\n"
        		+ "  </form>");
        

        out.println("</body></html>");
		
		
	}

}
