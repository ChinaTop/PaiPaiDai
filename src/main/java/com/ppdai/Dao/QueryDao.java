package com.ppdai.Dao;

import com.ppdai.POJO.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    public List<Query> finds(
            Integer page, Integer pagesize, String sort, String order,
            String type, Date date,String username) {
        List<Query> userslist = new ArrayList<Query>();

        try {
            conn = DBUtil.openConnection();
            String sql = "select id,username,querytype,userexpenditure,userdeposit,remaining,queryexplain,querydate from usersquery ";
            sql += " INNER JOIN (select id from usersquery where username=? ";

            if (type != null && type.trim().length() > 0) {
                sql += " and querytype = ? ";
            }

            if(date !=null){
                sql += " and querydate > ? ";
            }


            sql += "ORDER BY " + sort + " " + order + " LIMIT " + page + ", " +
                    pagesize + ") AS lim USING(id)";

            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            int index = 2;
            if (type != null && type.trim().length() > 0) {
                ps.setString(index, type );
                index++;
            }
            if(date !=null){
                ps.setDate(index, new java.sql.Date(date.getTime()));
              }
            rs = ps.executeQuery();
            while (rs.next()) {
                Query query = new Query();
                query.setId(rs.getInt("id"));
                query.setUsername(rs.getString("username"));
                query.setDate(rs.getDate("querydate"));
                query.setType(rs.getString("querytype"));
                query.setExpenditure(rs.getDouble("userexpenditure"));
                query.setDeposit(rs.getDouble("userdeposit"));
                query.setRemaining(rs.getDouble("remaining"));
                query.setExplain(rs.getString("queryexplain"));
                userslist.add(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return userslist;
    }




    public Integer getMaxPage(Integer pagesize, String type,Date date,String username) {
        Integer count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "select count(id) from usersquery where username=? ";
            if (type != null && type.trim().length() > 0) {
                sql += " and querytype = ? ";
            }
            if(date !=null){
                sql += " and querydate > ? ";
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            int index = 2;
            if (type != null && type.trim().length() > 0) {
                ps.setString(index, type);
                index++;
            }
            if(date !=null){
                ps.setDate(index,new java.sql.Date(date.getTime()));
            }
            System.out.println(sql);
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1); // 获取总记录条数
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        // 总页数
        return (count % pagesize == 0) ?
                count / pagesize : count / pagesize + 1;
    }



    public Integer recharge(String username,Double rechargeamount,Double remaining) {
        Double remain = remaining + rechargeamount;
        Integer count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "update users set remaining=? where username=? ";
            String sql2 = "INSERT INTO usersquery(username,querytype,userdeposit,remaining,queryexplain,querydate) VALUES (?,?,?,?,?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, remain);
            ps.setString(2, username);
            ps.executeUpdate();
            ps = conn.prepareStatement(sql2);
            ps.setString(1, username);
            ps.setString(2, "充值");
            ps.setDouble(3, rechargeamount);
            ps.setDouble(4, remain);
            ps.setString(5, "充值" + rechargeamount + "元");
            ps.setDate(6, new java.sql.Date(new Date().getTime()));
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return count;
    }


    public Integer withdraw(String username,Double withdrawamount,Double remaining) {
        Double remain = remaining - withdrawamount;
        Integer count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "update users set remaining=? where username=? ";
            String sql2 = "INSERT INTO usersquery(username,querytype,userexpenditure,remaining,queryexplain,querydate) VALUES (?,?,?,?,?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, remain);
            ps.setString(2, username);
            ps.executeUpdate();
            ps = conn.prepareStatement(sql2);
            ps.setString(1, username);
            ps.setString(2, "提现");
            ps.setDouble(3, withdrawamount);
            ps.setDouble(4, remain);
            ps.setString(5, "提现" + withdrawamount + "元");
            ps.setDate(6, new java.sql.Date(new Date().getTime()));
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return count;
    }





}
