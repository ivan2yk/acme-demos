package com.acme.servlet;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.acme.ejb.HolaLocal;

@WebServlet("/HolaServletEJB")
public class HolaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 2345302477267864140L;
	
	@EJB
	HolaLocal miobjeto;
	 
	 public HolaServlet() {
	 super();
	 }
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
	 PrintWriter pw=response.getWriter();
	 pw.println(miobjeto.mensaje());
	 pw.close();
	 
	 }
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
	}
	
}