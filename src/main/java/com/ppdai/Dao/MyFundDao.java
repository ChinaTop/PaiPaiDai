package com.ppdai.Dao;

import com.ppdai.POJO.Fund;
import com.ppdai.POJO.Myfund;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyFundDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public int add(String name,String username,Double rate,Double investamount,Date buydate, Integer deadline) {
        int count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "insert into myfund(name,rate,investamount,deadline,buydate,username) values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2,rate);
            ps.setDouble(3,investamount);
            ps.setInt(4,deadline);
            ps.setDate(5, new java.sql.Date(buydate.getTime()));
            ps.setString(6,username);
            count = ps.executeUpdate(); // 返回值 1 成功; 0 失败
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return count;
    }




    public List<Myfund> findMyFund(String username) {
        List<Myfund> myfunds = new ArrayList<Myfund>();
        try {
            conn = DBUtil.openConnection();
            String sql = "SELECT * FROM myfund WHERE username=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            while (rs.next()) {
                Myfund myfund = new Myfund();
                myfund.setMyfundid(rs.getInt("myfundid")); // 根据字段索引获取值
                myfund.setName(rs.getString("name")); // 根据字段名获取值
                myfund.setRate(rs.getDouble("rate"));
                myfund.setInvestamount(rs.getDouble("investamount"));
                myfund.setBuydate(rs.getDate("buydate"));
                myfund.setDeadline(rs.getInt("deadline"));
                myfund.setIffinish(rs.getInt("iffinish"));
                myfunds.add(myfund);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return myfunds;
    }




    public int finished(Integer myfundid) {
        int count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "update myfund set iffinish=1 where myfundid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,myfundid);
            count = ps.executeUpdate(); // 返回值 1 成功; 0 失败
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return count;
    }





    public int myFundRecord(String username,String name, Double remaining,Double backmoney,Date date){
        int count=0;
        conn = DBUtil.openConnection();
        String sql="INSERT INTO usersquery(username,querytype,userdeposit,remaining,queryexplain,querydate) VALUES (?,?,?,?,?,?) ";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,"基金收回");
            ps.setDouble(3,backmoney);
            ps.setDouble(4,remaining);
            ps.setString(5,"收回基金"+name);
            ps.setDate(6,new java.sql.Date(date.getTime()));
            count=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }




    public int removeMyFund(Integer myfundid) {
        int count = 0;
        conn = DBUtil.openConnection();
        String sql = "delete from myfund where myfundid=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, myfundid);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return count;
    }


}
