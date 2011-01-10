/**
 * Eclipse built in java conventions used.
 */
package com.opower.database;

import java.sql.Connection;

public interface ConnectionPool {
	Connection getConnection() throws java.sql.SQLException;

	void releaseConnection(Connection con) throws java.sql.SQLException;
}
