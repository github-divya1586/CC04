package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.configurations.AppConfig;
import com.model.EmailModel;

/**
 * Servlet implementation class EmailServlet
 */
@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String fromName=request.getParameter("fromMail");
		String toName=request.getParameter("toMail");
		String subject=request.getParameter("subject");
		String keywords=request.getParameter("keywords");
		String dept=request.getParameter("dept");
		String body=request.getParameter("body");
		
		
		
		EmailModel em=AppConfig.getEmailModel();
		
		em.setFromEmail(fromName);em.setToEmail(toName);
		em.setSubject(subject); em.setDept(dept);em.setBody(body);
    	em.setKeywords(keywords.split(","));	
    	
    	 try {
			boolean status=AppConfig.getEmailService().insertEmail(em);
			if(status) {
				System.out.println("success");
				 String info = "<div class=\"alert alert-success wrap-input100\">\n" +
		                    "                        <p style=\"font-family: Ubuntu-Bold; font-size: 18px; margin: 0.25em 0; text-align: center; color:#000;\">\n" +
		                    "                            Email sent Successfully!\n" +
		                    "                        </p>\n" +
		    
		                    "                    </div>";
				request.setAttribute("info", info);
	            request.getRequestDispatcher("sendemail.jsp?page=send").forward(request, response);
			}else {
				System.out.println("failed");
				 String alert = "<div class=\"alert alert-success wrap-input100\">\n" +
		                    "                        <p style=\"font-family: Ubuntu-Bold; font-size: 18px; margin: 0.25em 0; text-align: center\">\n" +
		                    "                            Something went wrong Try Again!\n" +
		                    "                        </p>\n" +
		                    "<a href=\"login.jsp\">Login</a> "+
		                    "                    </div>";
				request.setAttribute("alert",alert);
	            request.getRequestDispatcher("sendemail.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
		
		
		
	}

}
