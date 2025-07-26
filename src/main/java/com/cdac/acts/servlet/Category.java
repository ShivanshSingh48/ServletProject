package com.cdac.acts.servlet;

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

@WebServlet("/Category")
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection dbconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac", "root",
					"shivanshpc");
			PreparedStatement psproduct = dbconnection.prepareStatement("select * from category");

			PrintWriter out = response.getWriter();

			ResultSet result = psproduct.executeQuery();

			out.println("<html>");
			out.println("<body>");
			out.println("<table border='1'>");
			out.println("<tr>");
			out.println("<th>Category ID</th>");
			out.println("<th>Category Type</th>");
			out.println("<th>Category Description</th>");
			out.println("<th>Category image</th>");
			out.println("</tr>");

			out.println("<tbody>");
			while (result.next()) {
				out.println("<tr>");
				out.println("<td><a href='products?CategoryID=" + result.getString("CategoryID") + "'>" + result.getString("CategoryID") + "</a></td>");
				out.println("<td>" + result.getString("Name") + "</td>");
				out.println("<td>" + result.getString("Description") + "</td>");
				out.println("<td><img src='./image/" + result.getString("Image") + "' width='60px' height='60px' /></td>");
				out.println("</tr>");
			}

			out.println("</table>");
			out.println("</body>");
			out.println("</html>");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
