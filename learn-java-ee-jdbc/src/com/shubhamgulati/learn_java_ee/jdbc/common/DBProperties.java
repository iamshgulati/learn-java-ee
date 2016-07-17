package com.shubhamgulati.learn_java_ee.jdbc.common;

public interface DBProperties {
	
	// for Windows
	String DB_PROPERTIES_FILE_PATH = "D:\\Work\\GitHub\\learn-java-ee\\src\\com\\shubhamgulati\\jee\\jdbc\\common\\DBProperties.properties";
	
	// for Linux
	// String DB_PROPERTIES_FILE_PATH = "/home/shubham/Work/GitHub/learn-java-ee/src/com/shubhamgulati/jdbc/common/DBProperties.properties";
	
	String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	String DB_CONNECTION_URL = "jdbc:mysql://127.0.0.1:3306/bece6_db";
}
