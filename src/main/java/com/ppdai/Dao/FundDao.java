package com.ppdai.Dao;

import com.ppdai.POJO.Fund;
import com.ppdai.POJO.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FundDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Fund> findfund() {
        List<Fund> funds = new ArrayList<Fund>();
        try {
            conn = DBUtil.openConnection();
            String sql = "SELECT * FROM fund WHERE risklevel='保守型'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Fund fund = new Fund();
                fund.setFundid(rs.getInt("fundid")); // 根据字段索引获取值
                fund.setName(rs.getString("name")); // 根据字段名获取值
                fund.setRisklevel(rs.getString("risklevel"));
                fund.setRate(rs.getDouble("rate"));
                fund.setInvestamount(rs.getDouble("investamount"));
                fund.setInvestdate(rs.getDate("investdate"));
                fund.setDeadline(rs.getInt("deadline"));
                fund.setInvestman(rs.getInt("investman"));
                funds.add(fund);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return funds;
    }


    //前端显示并筛选查询所有基金项目
    public List<Fund> findfund(Integer page,String sort,String order,String risklevel, Integer rate1,
                               Integer rate2,Integer investamount1,Integer investamount2){
        List<Fund> funds = new ArrayList<Fund>();
        try {
            conn = DBUtil.openConnection();
            String sql = "SELECT * FROM fund ";
            sql+= " INNER JOIN (select fundid from fund where ifdisplay=1 ";
            if (risklevel != null && risklevel.trim().length()>0) {
                sql += " and risklevel = ? ";
            }
            if (rate1>0 && rate2>0) {
                sql += " and rate >= ? and rate <= ? ";
            }
            if ( investamount1>0 && investamount2>0) {
                sql += " and investamount >= ? and investamount <= ? ";
            }
            sql+= " order by "+ sort + " "+ order +" limit "+ page +", 5 ) AS lim USING(fundid)";
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (risklevel != null && risklevel.trim().length() > 0) {
                ps.setString(index, risklevel);
                index++;
            }
            if (rate1>0 && rate2>0) {
                ps.setInt(index,rate1);
                ps.setInt(index+1,rate2);
                index=index+2;
            }
            if(investamount1>0 && investamount2>0){
                ps.setInt(index,investamount1);
                ps.setInt(index+1,investamount2);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Fund fund = new Fund();
                fund.setFundid(rs.getInt("fundid")); // 根据字段索引获取值
                fund.setName(rs.getString("name")); // 根据字段名获取值
                fund.setRisklevel(rs.getString("risklevel"));
                fund.setRate(rs.getDouble("rate"));
                fund.setInvestamount(rs.getDouble("investamount"));
                fund.setInvestdate(rs.getDate("investdate"));
                fund.setDeadline(rs.getInt("deadline"));
                fund.setInvestman(rs.getInt("investman"));
                funds.add(fund);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return funds;
    }


    //基金分页查询所需要的最大页数
    public Integer getMaxPage(String risklevel, Integer rate1,Integer rate2,
                              Integer investamount1,Integer investamount2) {
        Integer count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "select count(fundid) from fund where 1=1 ";
            if (risklevel != null && risklevel.trim().length() > 0) {
                sql += " and risklevel = ? ";
            }
            if (rate1>0 && rate2>0) {
                sql += " and rate >= ? and rate <= ? ";
            }
            if ( investamount1>0 && investamount2>0) {
                sql += " and investamount >= ? and investamount <= ? ";
            }
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (risklevel != null && risklevel.trim().length() > 0) {
                ps.setString(index, risklevel);
                index++;
            }
            if (rate1>0 && rate2>0) {
                ps.setInt(index,rate1);
                ps.setInt(index+1,rate2);
                index=index+2;
            }
            if(investamount1>0 && investamount2>0){
                ps.setInt(index,investamount1);
                ps.setInt(index+1,investamount2);
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
        return (count % 5 == 0) ?
                count / 5 : count / 5 + 1;
    }


    //显示基金筛选查询后的记录条数
    public Integer getCount(String risklevel, Integer rate1,Integer rate2,
                              Integer investamount1,Integer investamount2) {
        Integer count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "select count(fundid) from fund where 1=1 ";
            if (risklevel != null && risklevel.trim().length() > 0) {
                sql += " and risklevel = ? ";
            }
            if (rate1>0 && rate2>0) {
                sql += " and rate >= ? and rate <= ? ";
            }
            if ( investamount1>0 && investamount2>0) {
                sql += " and investamount >= ? and investamount <= ? ";
            }
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (risklevel != null && risklevel.trim().length() > 0) {
                ps.setString(index, risklevel);
                index++;
            }
            if (rate1>0 && rate2>0) {
                ps.setInt(index,rate1);
                ps.setInt(index+1,rate2);
                index=index+2;
            }
            if(investamount1>0 && investamount2>0){
                ps.setInt(index,investamount1);
                ps.setInt(index+1,investamount2);
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
        return count;
    }




    //某个用户购买了基金则基金购买人数+1
    public int fundRecord( Integer investman,Integer fundid){
        int count=0;
        conn = DBUtil.openConnection();
        Integer newinvestman=investman+1;
        String sql=" Update fund set investman=? where fundid=? ";
        System.out.println(newinvestman);
        System.out.println(fundid);
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1,newinvestman);
            ps.setInt(2,fundid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
}


    //把某个用户的一项基金购买写入其资金表
    public int fundRecord2(String username,String name, Double remaining,Double investamount,Date date){
        int count=0;
        conn = DBUtil.openConnection();
        String sql="INSERT INTO usersquery(username,querytype,userexpenditure,remaining,queryexplain,querydate) VALUES (?,?,?,?,?,?) ";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,"基金购买");
            ps.setDouble(3,investamount);
            ps.setDouble(4,remaining);
            ps.setString(5,"购买一份"+name);
            ps.setDate(6,new java.sql.Date(date.getTime()));
            count=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }




    //后台查询基金
    public List<Fund> display(){
        List<Fund> funds = new ArrayList<Fund>();
        try {
            conn = DBUtil.openConnection();
            String sql = "SELECT * FROM fund ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Fund fund = new Fund();
                fund.setFundid(rs.getInt("fundid")); // 根据字段索引获取值
                fund.setName(rs.getString("name")); // 根据字段名获取值
                fund.setRisklevel(rs.getString("risklevel"));
                fund.setRate(rs.getDouble("rate"));
                fund.setInvestamount(rs.getDouble("investamount"));
                fund.setInvestdate(rs.getDate("investdate"));
                fund.setDeadline(rs.getInt("deadline"));
                fund.setInvestman(rs.getInt("investman"));
                fund.setIfdisplay(rs.getInt("ifdisplay"));
                funds.add(fund);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return funds;
    }

    //基金下架
    public int xiajia(Integer fundid) {
        int count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "update fund set ifdisplay=0 where fundid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,fundid);
            count = ps.executeUpdate(); // 返回值 1 成功; 0 失败
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return count;
    }



    //基金上架
    public int shangjia(Integer fundid) {
        int count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "update fund set ifdisplay=1 where fundid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,fundid);
            count = ps.executeUpdate(); // 返回值 1 成功; 0 失败
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return count;
    }




    //后台添加新的基金项目
    public int add(String name,String risklevel,Double rate,Double investamount,Date investdate,
                   Integer deadline,Integer investman,Integer ifdisplay) {
        int count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "insert into fund(name,risklevel,rate,investamount,investdate,deadline,investman,ifdisplay) values(?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2,risklevel);
            ps.setDouble(3,rate);
            ps.setDouble(4,investamount);
            ps.setDate(5, new java.sql.Date(investdate.getTime()));
            ps.setInt(6,deadline);
            ps.setInt(7,investman);
            ps.setInt(8,ifdisplay);
            count = ps.executeUpdate(); // 返回值 1 成功; 0 失败
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return count;
    }


}

