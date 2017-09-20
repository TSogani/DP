package com.cdp.beans;

import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.util.Cache;

@WebServlet("/ViewRegistrationServlet")
public class ViewRegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Cache cache = Cache.getInstance();

		Connection con=null;;

		Statement stmt=null;
		ResultSet rs=null;

		Statement stmt1=null;
		ResultSet rs1=null;

		Statement stmt2=null;
		ResultSet rs2=null;

		Map<String, Object> cities = null;
		cities = new HashMap<String, Object>();

		Map<String, Object> states = null;
		states = new HashMap<String, Object>();

		Map<String, Object> countrys = null;
		countrys = new HashMap<String, Object>();

		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			// Driver d = new oracle.jdbc.driver.OracleDriver();
			// DriverManager.registerDriver(d);
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "bms", "abc");

			if (cache.containsKey("cities") == false) {

				stmt = con.createStatement();
				rs = stmt.executeQuery("select * from city_tbl");
				while (rs.next()) {
					cities.put(rs.getString("CITY_CD"),
							rs.getString("CITY_NAME"));
				}
				cache.put("cities", cities);
			}
			if (cache.containsKey("states") == false) {

				stmt1 = con.createStatement();
				rs1 = stmt1.executeQuery("select * from state_tbl");
				while (rs1.next()) {
					states.put(rs1.getString("STATE_CD"),
							rs1.getString("STATE_NAME"));
				}
				System.out.println("state if ");
				cache.put("states", states);

			}
			if (cache.containsKey("countrys") == false) {
				stmt2 = con.createStatement();
				rs2 = stmt2.executeQuery("select * from country_tbl");
				while (rs2.next()) {
					countrys.put(rs2.getString("COUNTRY_CD"),
							rs2.getString("COUNTRY_NAME"));
				}

				cache.put("countrys", countrys);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(con != null){
				con.close();
				}
			}catch(SQLException e){
				
				e.printStackTrace();
			}
		}

		Object citiess =   cache.get("cities");
		Object statess =  cache.get("states");
		Object countryss =  cache.get("countrys");

		request.setAttribute("citiess", citiess);
		request.setAttribute("statess", statess);
		request.setAttribute("countryss", countryss);
		
		request.getRequestDispatcher("/Registration.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String fname = request.getParameter("fn");
		String lname = request.getParameter("ln");
		String gender = request.getParameter("gen");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");

		System.out.println(fname);
		System.out.println(lname);
		System.out.println(gender);
		System.out.println(city);
		System.out.println(state);
		System.out.println(country);

	}
}