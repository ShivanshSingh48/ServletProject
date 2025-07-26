package com.cdac.acts.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.print.event.PrintJobEvent;

@WebServlet("/Authentication")
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection dbconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac", "root",
					"shivanshpc");
			if (dbconnection != null) {
				System.out.println("hogaya");
			}
			PreparedStatement ps = dbconnection
					.prepareStatement("select * from users where username =? and password=?");

			String name = request.getParameter("username");
			String password = request.getParameter("password");

			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("LoggedInUser", name);
				session.setMaxInactiveInterval(120);
				response.sendRedirect("Category");

			} else {
				response.getWriter().println("Fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
