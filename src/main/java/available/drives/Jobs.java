package available.drives;
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

public class Jobs extends HttpServlet {
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet res=null;
	ResultSet res1=null;
	int n;
	String url="jdbc:mysql://localhost:3306/rollnumber";
	String un="root";
	String ps="bhargavi@123";
	String qry="select * from result where rollnumber=(?)";
	String qry1="select * from jobs where frontend<(?)";
	
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,un,ps);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
		}
		
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			pstmt=con.prepareStatement(qry);
			String num=req.getParameter("rn");
			int rollnumber=Integer.parseInt(num);
			pstmt.setInt(1, rollnumber);
			res=pstmt.executeQuery();
//			System.out.println("query executed");
			if(res.next()) {
				n=res.getInt(5);
				System.out.println(n);
			}
			
			pstmt=con.prepareStatement(qry1);
			pstmt.setDouble(1,n);
			res1=pstmt.executeQuery();
			System.out.println("query executed");
			PrintWriter out=resp.getWriter();
//			if(res1.next()) {
//				n=res1.getInt(3);
//				System.out.println(n);
//			}
			out.println(	"<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>Document</title>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "    <table border=2>\r\n"
					+ "        <tr>\r\n"
					+ "            <th>Slno</th>\r\n"
					+ "            <th>OrgName</th>\r\n"
					+ "            <th>Designation</th>\r\n"
					+ "            <th>Package</th>\r\n"
					+ "            <th>JavaCo</th>\r\n"
					+ "            <th>SQL</th>\r\n"
					+ "            <th>FrontEnd</th>\r\n"
					+ "            <th>Location</th>\r\n"
					+ "            <th>Passout</th>\r\n"
					+ "        </tr>\r\n"
					+ "\r\n");
		
			while(res1.next()) {
				
				out.println(
						 "        <tr>\r\n"
						+ "            <td>"+res1.getInt(1)+"</td>\r\n"
						+ "            <td>"+res1.getString(2)+"</td>\r\n"
						+ "            <td>"+res1.getString(3)+"</td>\r\n"
						+ "            <td>"+res1.getInt(4)+"</td>\r\n"
						+ "            <td>"+res1.getInt(5)+"</td>\r\n"
						+ "            <td>"+res1.getInt(6)+"</td>\r\n"
						+ "            <td>"+res1.getInt(7)+"</td>\r\n"
						+ "            <td>"+res1.getString(8)+"</td>\r\n"
						+ "            <td>"+res1.getInt(9)+"</td>\r\n"
						+ "        </tr>\r\n"
						
						);
				
			}
			out.println( "\r\n"
					+ "    </table>\r\n"
					+ "</body>\r\n"
					+ "</html>");
			
			} catch (SQLException e) {
			
		}
	}

	

}


