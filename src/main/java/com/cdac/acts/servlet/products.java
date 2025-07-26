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
import java.sql.SQLException;

@WebServlet("/products")
public class products extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection dbconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac", "root",
					"shivanshpc");
			PreparedStatement psproduct = dbconnection.prepareStatement("select * from product where CategoryID=? ");

			psproduct.setInt(1,Integer.parseInt(request.getParameter("CategoryID")));

			PrintWriter out = response.getWriter();

			ResultSet result = psproduct.executeQuery();

			out.println("<html>");
			out.println("<body>");
			out.println("<table border='1'>");
			out.println("<tr>");
			out.println("<th>Products ID</th>");
			out.println("<th>Category ID</th>");
			out.println("<th>Products Name</th>");
			out.println("<th>Product Price</th>");
			out.println("<th>Products Quantity</th>");
			out.println("</tr>");

			out.println("<tbody>");
			while (result.next()) {
				out.println("<tr>");
				out.println("<td>"+result.getString("productID")+"</td>");
				out.println("<td>" + result.getString("CategoryID") + "</td>");
				out.println("<td>" + result.getString("productName") + "</td>");
				out.println("<td>"+result.getString("productPrice")+"</td>");
				out.println("<td>"+result.getString("productQuantity")+"</td>");
				out.println("</tr>");
			}

			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
