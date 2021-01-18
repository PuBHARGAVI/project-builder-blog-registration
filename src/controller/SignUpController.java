package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import model.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import businesslogic.RegistrationValidation;





@WebServlet(urlPatterns= {"/signup"})
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SignUpController() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=this.getServletContext().getRequestDispatcher("/WEB-INF/views/signupView.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String email = request.getParameter("email"); 
		String password = request.getParameter("password"); 
		String confirmPassword = request.getParameter("confirmPassword"); 
		LocalDateTime date= LocalDateTime.now(); 
		
		RegistrationValidation checkUser=new RegistrationValidation();
		
		if(checkUser.checkUserDetails(email, password, confirmPassword))
		{
			User user=new User(email,password,date);
			user.setEmail(email);
			user.setPassword(password);
			user.setDate(date);
			System.out.println(user.getEmail());
			System.out.println(user.getPassword());
			System.out.println(user.getDate());
			request.setAttribute("message", "Registration Successful");
			RequestDispatcher rd=this.getServletContext().getRequestDispatcher("/WEB-INF/views/signupView.jsp");
			rd.forward(request, response);
		}
		else
		{
			request.setAttribute("message", "Check your email and password");
			RequestDispatcher rd=this.getServletContext().getRequestDispatcher("/WEB-INF/views/signupView.jsp");
			rd.forward(request, response);
		}
		
	}

}
