package com.besant;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResultChecking extends HttpServlet {
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet res=null;
	String url="jdbc:mysql://localhost:3306/rollnumber";
	String un="root";
	String ps="bhargavi@123";
	String qry="select * from result where rollnumber=(?)";
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,un,ps);
			pstmt=con.prepareStatement(qry);
			String num=req.getParameter("rn");
			int rollnumber=Integer.parseInt(num);
			System.out.println(rollnumber);
			pstmt.setInt(1, rollnumber);
			res=pstmt.executeQuery();
			System.out.println("executed successfully");
			
			if(res.next())
			{
//				System.out.println(res.getString(1)+" "+res.getInt(2)+" "+res.getInt(3)+" "+res.getInt(4)+" "+res.getInt(5));
				PrintWriter out=resp.getWriter();
				
				out.println("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Document</title>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "    <table border=\"2px\">\r\n"
						+ "        <tr>\r\n"
						+ "            <th>Name</th>\r\n"
						+ "            <th>Roll Number</th>\r\n"
						+ "            <th>FrontEnd</th>\r\n"
						+ "            <th>Java</th>\r\n"
						+ "            <th>SQL</th>\r\n"
						+ "        </tr>\r\n"
						+ "\r\n"
						+ "        <tr>\r\n"
						+ "            <td>"+res.getString(1)+"</td>\r\n"
						+ "            <td>"+res.getInt(2)+"</td>\r\n"
						+ "            <td>"+res.getInt(3)+"</td>\r\n"
						+ "            <td>"+res.getInt(4)+"</td>\r\n"
						+ "            <td>"+res.getInt(5)+"</td>\r\n"
						+ "        </tr>\r\n"
						+ "\r\n"
						+ "    </table>\r\n"
						+ "\r\n"
						+ "</body>\r\n"
						+ "</html>");
			}
			else {
				PrintWriter out=resp.getWriter();
				out.println("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Document</title>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "  <h1>Invalid Roll Number</h1>\r\n"
						+ "\r\n"
						+ "</body>\r\n"
						+ "</html>");
			}
			
		
		} catch (SQLException |NullPointerException | ClassNotFoundException e) {
		
		}
		
	
		
	}

	

}
