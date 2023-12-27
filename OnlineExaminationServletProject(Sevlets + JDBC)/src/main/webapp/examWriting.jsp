<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
    function validateForm() {
        var answers = document.querySelectorAll('input[type=radio]:checked');
        if (answers.length < 6) { // Check the number of questions (modify as needed)
            alert('Please answer all questions before submitting.');
            return false; // Prevent form submission
        }
        return true; // Allow form submission
    }
</script>

</head>
<body>

 <h1>Servlet Exam</h1>

    
    <form action="VerifyAnswersServlet" method="post" onsubmit="return validateForm()">
    <p>1. What is a servlet?</p>
        <input type="radio" name="q1" value="a"> a) A type of coffee
        <input type="radio" name="q1" value="b"> b) A server-side Java program
        <input type="radio" name="q1" value="c"> c) A type of dessert

        <p>2. Which interface should a Java servlet implement?</p>
        <input type="radio" name="q2" value="a"> a) Runnable
        <input type="radio" name="q2" value="b"> b) HttpServlet
        <input type="radio" name="q2" value="c"> c) List

        <p>3. What method is called to handle a GET request in a servlet?</p>
        <input type="radio" name="q3" value="a"> a) doPost()
        <input type="radio" name="q3" value="b"> b) service()
        <input type="radio" name="q3" value="c"> c) doGet()


        
        <p>4. Which method of the `HttpServlet` class is used to handle GET requests?</p>
		<input type="radio" name="q4" value="a"> a) `doGet()`
		<input type="radio" name="q4" value="b"> b) `doPost()`
		<input type="radio" name="q4" value="c"> c) `service()`

		<p>5. What is the purpose of the `web.xml` deployment descriptor in a servlet-based web application?</p>
		<input type="radio" name="q5" value="a"> a) To define the Java packages used in the project
		<input type="radio" name="q5" value="b"> b) To specify URL patterns and servlet mappings
		<input type="radio" name="q5" value="c"> c) To store image and CSS files

		<p>6. Which HTTP status code is typically used to indicate a successful response in a servlet?</p>
		<input type="radio" name="q6" value="a"> a) 200 OK
		<input type="radio" name="q6" value="b"> b) 404 Not Found
		<input type="radio" name="q6" value="c"> c) 500 Internal Server Error
        

    <input type="submit" value="Submit">
</form>



</body>
</html>