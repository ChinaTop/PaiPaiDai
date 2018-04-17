package com.ppdai.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * dao:data acccss object 数据访问对象
 * 封装对Contacts 的操作 crud(增删改查)
 */
public class UsersDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * 注册
     */
    public int add(String username, String password, String id, String bankid) {
        int count = 0;
        conn = DBUtil.openConnection();
        String sql = "insert into users(username,password,id,bankid) values(?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, id);
            ps.setString(4, bankid);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return count;
    }

    /**
     * 登录 根据输入id查找密码是否存在
     */
    public String find(String username) {
        String password = null;
        conn = DBUtil.openConnection();
        String sql = "select password from users where username=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                password = rs.getString("password");
                System.out.println(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return password;
    }


    //根据用户名查询余额
    public double remaincheck(String username){
        double remaining=0.00;
        conn= DBUtil.openConnection();
        String sql="SELECT remaining FROM users WHERE username=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            while(rs.next()) {
                remaining = rs.getDouble("remaining");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return remaining;
    }

    //根据用户名取得银行帐号
    public String getBankId(String username){
        String bankid=null;
        conn= DBUtil.openConnection();
        String sql="SELECT bankid FROM users WHERE username=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            while(rs.next()) {
                bankid = rs.getString("bankid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return bankid;
    }


    //取出所有用户名
    public List<String> usernamematch() {
        List<String> usernamelist = new ArrayList<String>();
        try {
            conn = DBUtil.openConnection();
            String sql = "SELECT username FROM users WHERE 1=1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                usernamelist.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return usernamelist;
    }


    //修改账户余额
    public int remainingChange(String username,Double remaining){
        int count=0;
        conn = DBUtil.openConnection();
        String sql="Update users set remaining=? where username=? ";
        try {
            ps = conn.prepareStatement(sql);
            ps.setDouble(1,remaining);
            ps.setString(2,username);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


}