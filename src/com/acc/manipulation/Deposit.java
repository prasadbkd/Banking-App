package com.acc.manipulation;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/Deposit")
public class Deposit extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int amt = Integer.parseInt(request.getParameter("damt"));
		
		try {
			
			Connection con = connect.getConnection();
			String sql="UPDATE accounts SET amount = amount +"+amt+" WHERE accno='ac01' ";
			
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			
			
			String sql2 = "Insert into transactions(accno,type,amount) values('acc01','Credited',?)"; 
			PreparedStatement st2 = con.prepareStatement(sql2);
			    st2.setInt(1, amt);
			    st2.executeUpdate();
			    st.close();
			    con.close();
		} 
		catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		response.sendRedirect("Account.jsp");
	
	}

	
	

}
