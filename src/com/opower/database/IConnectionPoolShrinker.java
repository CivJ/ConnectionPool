package com.opower.database;

public interface IConnectionPoolShrinker {
	/**
	 * TODO take some useful input
	 * @return number of connections to remove from the pool
	 */
	public int shrinkPool();
}
