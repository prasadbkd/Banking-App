package com.acc.manipulation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Tranfer
 */
@WebServlet("/Tranfer")
public class Tranfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int amt = Integer.parseInt(request.getParameter("tamt"));//Retrieving the parameters passed through the form
		
		try {
			Connection con = connect.getConnection();//The static class connect returns the database connection object,is stored in "con" object
			
			
			Statement st1 = con.createStatement();
			String sql1="select * from accounts";
			ResultSet rs = st1.executeQuery(sql1);
			int bal = 0;
			if(rs.next()) {
				 bal = rs.getInt("amount");
			}
			if(bal>amt) {   //This condition checks if the give amount to transfer is less than the balance or not
			String sql="UPDATE accounts SET amount = amount -"+amt+" WHERE accno='ac01' "; //The query to perform Transfer 
			
			Statement st = con.createStatement();
			st.executeUpdate(sql);//The required manipulation is performed by this method
			String sql2 = "Insert into transactions(accno,type,amount) values('acc01','Transfered',?)";//The prepared statement query used for recording Transactions 
			PreparedStatement st2 = con.prepareStatement(sql2);
			    st2.setInt(1, amt);
			    st2.executeUpdate(); //Executing this method records the transaction in transaction table 
		    //closing the connections to ensure no memory leak occurs	    
			    st.close();
			    con.close();
			    response.sendRedirect("Account.jsp");//This statement is used to redirect to the required JSP page
				
			}	    
		
			else {
				RequestDispatcher rd=request.getRequestDispatcher("/Account.jsp"); //If the above "if" condition fails then it gets redirected to the Account page
		        rd.include(request, response);  
		        PrintWriter out = response .getWriter();
				out.println("<br><h5 style='color:Tomato'>INSUFFICIENT FUNDS</h5>");//Prints the error message in the Account page
			}
			
			
		
		} 
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

	

}