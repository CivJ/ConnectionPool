package com.opower.database;

import java.sql.Connection;
import java.sql.SQLException;
//TODO have user pass in args on command line?
/**
 * A driver
 */
public class Runner {
	public static void main(String[] args) throws SQLException {
		//TODO make url configed or passed in
		String url = "jdbc:mysql://localhost:3306/opower";
		
		ConnectionPool pool = new ConnectionPoolOpower(url, "root","");
		Connection c = pool.getConnection();
		
	}
}
