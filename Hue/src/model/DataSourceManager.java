package model;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DataSourceManager {
	private DataSource dataSource;
	private static DataSourceManager instance = new DataSourceManager();
	private DataSourceManager() {
		BasicDataSource dbcp = new BasicDataSource();
	     dbcp.setDriverClassName("oracle.jdbc.OracleDriver");
	     dbcp.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
	     dbcp.setUsername("scott");
	     dbcp.setPassword("tiger");
	     dbcp.setMaxTotal(20);
	     dataSource=dbcp;
	}
	
	public static DataSourceManager getInstance() {
		return instance;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
}
