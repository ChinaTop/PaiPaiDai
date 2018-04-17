package com.ppdai.Dao;
import sun.net.ConnectionResetException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static String driverName="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://127.0.0.1:3306/employee";
    private static String user="root";
    private static String password="root";

    public static Connection openConnection(){
        Connection conn=null;
        try {
            Class.forName(driverName);
            conn= DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }


    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}

