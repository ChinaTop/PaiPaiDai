package com.ppdai.Dao;

import com.ppdai.POJO.Loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LoanDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public int add(Loan loan) {
        int count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "insert into loan(username,loanamount,loandate,loantime,repayment) values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,loan.getUsername());
            ps.setDouble(2, loan.getLoanamount());
            ps.setDate(3, new java.sql.Date(loan.getLoandate().getTime()));
            ps.setInt(4, loan.getLoantime());
            ps.setDouble(5, loan.getRepayment());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return count;
    }



    public int gothrough(Integer loanid) {
        int count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "UPDATE loan SET ifchecked=1 WHERE loanid=? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, loanid);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return count;
    }




    public int loanRecord(String username,Double remaining,Double loanamount,Date date){
        int count=0;
        conn = DBUtil.openConnection();
        String sql="INSERT INTO usersquery(username,querytype,userdeposit,remaining,queryexplain,querydate) VALUES (?,?,?,?,?,?) ";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,"借款");
            ps.setDouble(3,loanamount);
            ps.setDouble(4,remaining);
            ps.setString(5,"借款"+loanamount+"元");
            ps.setDate(6,new java.sql.Date(date.getTime()));
            count=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }



    public int removeLoan(Integer loanid) {
        int count = 0;
        conn = DBUtil.openConnection();
        String sql = " delete from loan where loanid=? ";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, loanid);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return count;
    }

}
