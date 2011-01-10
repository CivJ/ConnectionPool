/**
 * A custom connection pool. 
 */

package com.opower.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.base.Preconditions;

//TODO make interfaces: growPool, shrinkPool, 

public class ConnectionPoolOpower implements ConnectionPool {

	/**
	 * Creates a connection pool with the given poolManager.
	 * 
	 * @param size - initial size of connection pool
	 * @throws SQLException 
	 */	
	public ConnectionPoolOpower(String driver, String url,
			String userName, String password, int size) throws SQLException {

		this.driver = Preconditions.checkNotNull(driver,
				"driver may not be null");
		this.url = Preconditions.checkNotNull(url, "url may not be null");
		this.userName = Preconditions.checkNotNull(userName,
				"user may not be null");
		this.password = Preconditions.checkNotNull(password,
				"password may not be null");
		
		if(connections == null){
			connections = new ConcurrentLinkedQueue<Connection>();
			if(size < 1)
				size = 10;
			
			for (int i = 0; i < size; i++) {
				connections.offer(getFromDriverManager());
				poolSize.incrementAndGet();
			}
		}
	}

	public Connection getConnection() throws SQLException {

		Connection c = connections.poll(); 
		try {
			if (c == null) // add some simple pool growth here.
				c = getFromDriverManager();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
	}

	public void releaseConnection(Connection con) throws SQLException {

		if (con == null)
			return;

		connections.offer(con);
		// TODO check status of connections
	}

	private Connection getFromDriverManager() throws SQLException {

		Preconditions.checkState(!url.isEmpty(),
				"the db connection string may not be empty");
		Preconditions.checkState(!userName.isEmpty(),
				"the user name may not be empty");

		// TODO delete this block?
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO logging?
			e.printStackTrace();
		}

		return DriverManager.getConnection(url, userName, password);
	}
	// TODO make these configurable
	// private final int CONNECTIONS_MIN = 5;
	// TODO private final int CONNECTIONS_MAX = 10;
	// private final int CONNECTIONS_MAX_IDLE = 10;
	private static AtomicInteger poolSize;
	private static AtomicLong lastCheckoutTime;
	private String	url;
	private String	userName;
	private String	password;
	private String	driver;
	private static  double avgCheckinTime; //TODO updates to this must be atomic
	private static Queue<Connection>	connections;

}
