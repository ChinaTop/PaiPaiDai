package com.ppdai.Dao;

import com.ppdai.POJO.Loan;
import com.ppdai.POJO.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminDao  {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public String find(String administrator){
        String adminpassword=null;
        conn= DBUtil.openConnection();
        String sql="select adminpassword from admin where administrator=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,administrator);
            rs=ps.executeQuery();
            while (rs.next()){
                adminpassword=rs.getString("adminpassword");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return adminpassword;
    }




    public Integer getMaxPage(Integer pagesize, String username,Date date) {
        Integer count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "select count(loanid) from loan where 1=1 ";
            if (username != null && username.trim().length() > 0) {
                sql += " and username = ? ";
            }
            if(date !=null){
                sql += " and loandate > ? ";
            }
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (username != null && username.trim().length() > 0) {
                ps.setString(index, username);
                index++;
            }
            if(date !=null){
                ps.setDate(index,new java.sql.Date(date.getTime()));
            }
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



    //后台查询待审核的借款项目
    public List<Loan> finds(
            Integer page, Integer pagesize,
            String username, Date date) {
        List<Loan> loanlist = new ArrayList<Loan>();

        try {
            conn = DBUtil.openConnection();
            String sql = "select loanid,username,loanamount,loantime,loandate,repayment from loan ";
            sql += " INNER JOIN (select loanid from loan where ifchecked=0 ";
            if (username != null && username.trim().length() > 0) {
                sql += " and username = ? ";
            }

            if(date !=null){
                sql += " and loandate > ? ";
            }


            sql += " LIMIT " + page + ", " +
                    pagesize + ") AS lim USING(loanid)";

            ps = conn.prepareStatement(sql);
            int index = 1;
            if (username != null && username.trim().length() > 0) {
                ps.setString(index, username );
                index++;
            }
            if(date !=null){
                ps.setDate(index, new java.sql.Date(date.getTime()));
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Loan loan = new Loan();
                loan.setLoanid(rs.getInt("loanid"));
                loan.setUsername(rs.getString("username"));
                loan.setLoandate(rs.getDate("loandate"));
                loan.setLoantime(rs.getInt("loantime"));
                loan.setLoanamount(rs.getDouble("loanamount"));
                loan.setRepayment(rs.getDouble("repayment"));
                loanlist.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return loanlist;
    }





    public Integer getMaxPage2(Integer pagesize, String username,Date date) {
        Integer count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "select count(id) from usersquery where 1=1 ";
            if (username != null && username.trim().length() > 0) {
                sql += " and username = ? ";
            }
            if(date !=null){
                sql += " and querydate > ? ";
            }
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (username != null && username.trim().length() > 0) {
                ps.setString(index, username);
                index++;
            }
            if(date !=null){
                ps.setDate(index,new java.sql.Date(date.getTime()));
            }
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




    //后台查询所有用户的资金情况
    public List<Query> findQuery(
            Integer page, Integer pagesize,
            String username, String sort,String order,Date date) {
        List<Query> querylist = new ArrayList<Query>();

        try {
            conn = DBUtil.openConnection();
            String sql = "select id,username,querytype,userexpenditure,userdeposit,remaining,querydate,queryexplain from usersquery ";
            sql += " INNER JOIN (select id from usersquery where 1=1 ";
            if (username != null && username.trim().length() > 0) {
                sql += " and username = ? ";
            }

            if(date !=null){
                sql += " and querydate > ? ";
            }
            sql += "ORDER BY " + sort + " " + order + " LIMIT " + page + ", " +
                    pagesize + ") AS lim USING(id)";
            System.out.println(sql);
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (username != null && username.trim().length() > 0) {
                ps.setString(index, username );
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
                query.setType(rs.getString("querytype"));
                query.setExpenditure(rs.getDouble("userexpenditure"));
                query.setDeposit(rs.getDouble("userdeposit"));
                query.setRemaining(rs.getDouble("remaining"));
                query.setExplain(rs.getString("queryexplain"));
                query.setDate(rs.getDate("querydate"));
                querylist.add(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return querylist;
    }



}
