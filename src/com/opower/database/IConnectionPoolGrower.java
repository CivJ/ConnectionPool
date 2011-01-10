package com.opower.database;

public interface IConnectionPoolGrower {
	/**
	 * TODO make this take some useful input, AVG?
	 * @return the number of connections to add to the pool
	 */
	public int growPool();
}
