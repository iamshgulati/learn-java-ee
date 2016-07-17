package com.shubhamgulati.learn_java_ee.jdbc.introduction;

import java.sql.*;
import java.util.Properties;

public class JDBCDemo {
	
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    /*// Bad way of coding dbUrl. User and password should not be hard-coded in the URL
    private static final String dbUrl = "jdbc:mysql://127.0.0.1:3306/bece6_db?user=root&password=root";*/
    private static final String dbUrl = "jdbc:mysql://127.0.0.1:3306/bece6_db";
    
    public static void main(String[] args) throws ClassNotFoundException,SQLException
    {
        System.out.println(driverClassName);
        Class.forName(driverClassName);
        Properties dbTestProperties = new Properties();
        dbTestProperties.put("user","root");
        dbTestProperties.put("password","root");
        Connection c = DriverManager.getConnection(dbUrl,dbTestProperties);
        System.out.println("It works!");
        c.close();
    }
}