package com.ppdai.Dao;



import com.ppdai.POJO.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public int images(Image image) {
        int count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "select max(loanid) from loan ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int c = 0;
            if (rs.next()) {
                c = rs.getInt("max(loanid)");
            }
            sql = "insert into image(loanid,usernameurl) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, c);
            ps.setString(2,image.getUsernameurl());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return count;
    }




    public String getUrl(Integer loanid){
        String usernameurl=null;
        try {
            conn = DBUtil.openConnection();
            String sql = "select usernameurl from image where loanid=? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,loanid);
            rs = ps.executeQuery();
            rs.next();
            usernameurl=rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return usernameurl;
    }





    public int removeImage(Integer loanid) {
        int count = 0;
        conn = DBUtil.openConnection();
        String sql = " delete from image where loanid=? ";
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
