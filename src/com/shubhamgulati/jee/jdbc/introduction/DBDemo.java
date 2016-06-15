package com.shubhamgulati.jee.jdbc.introduction;

import java.sql.*;
import java.util.Properties;

public class DBDemo
{
    private static final String dbClassName = "org.mariadb.jdbc.Driver";
    private static final String CONNECTION = "jdbc:mysql://127.0.0.1/emotherearth";
    public static void main(String[] args) throws ClassNotFoundException,SQLException
    {
        System.out.println(dbClassName);
        Class.forName(dbClassName);
        Properties p = new Properties();
        p.put("user","root");
        p.put("password","root");
        Connection c = DriverManager.getConnection(CONNECTION,p);
        System.out.println("It works !");
        c.close();
    }
}