package com.opower.test;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.opower.database.ConnectionPool;
import com.opower.database.ConnectionPoolOpower;

class ConnectThread extends Thread {
	public void run() {

		try {
			TestConnectionPoolOpower.connectionLease();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

// @RunWith(ConcurrentJunitRunner.class)
// @Concurrent(threads = 6)
public class TestConnectionPoolOpower {

	// These are run concurrently by the class in @RunWith
	/*
	 * @Test public void test0() throws SQLException{connectionLease();}
	 * 
	 * @Test public void test1() throws SQLException{connectionLease();}
	 * 
	 * @Test public void test2() throws SQLException{connectionLease();}
	 * 
	 * @Test public void test3() throws SQLException{connectionLease();}
	 * 
	 * @Test public void test4() throws SQLException{connectionLease();}
	 * 
	 * @Test public void test5() throws SQLException{connectionLease();}
	 */

	@Test
	public void concurrentConnections() throws Exception {

		final int THREADS = 100;
		for (int i = 0; i < THREADS; i++) {
			new ConnectThread().start();
		}
	}

	public static void connectionLease() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/opower";

	
		ConnectionPool pool = new ConnectionPoolOpower(
				"com.mysql.jdbc.Driver", url, "root", "", 10);
		Connection c = pool.getConnection();
		assertNotNull(c);

		// Thread.sleep(10000);
		// pool.releaseConnection(c);

	}

}
