package com.test.dao;

import com.test.entity.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import net.jdbc.JDBCUtils;

public class GradeDaoImpl implements GradeDao {
    @Override
    public List<Grade> SelectAllGrade() {
        List<Grade> listgrade = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Grade";
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String UserID = rs.getString("Sno");
                String TestID = rs.getString("TestID");
                int Grade = rs.getInt("Grade");
                Grade grade = new Grade();
                grade.setUserID(UserID);
                grade.setTestID(TestID);
                grade.setGrade(Grade);
                listgrade.add(grade);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            JDBCUtils.release(conn, ps, rs);
        }
        return listgrade;
    }

    @Override
    public int InsertGrade(Grade grade) {
        int rows = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into Grade values(?,?,?)";
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, grade.getUserID());
            ps.setString(2, grade.getTestID());
            ps.setInt(3, grade.getGrade());
            rows = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps, rs);
        }
        return rows;
    }

    @Override
    public int updateGrade(Grade grade) {
        int rows = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update Grade set Grade=? where TestID=? and Sno=?";
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, grade.getGrade());
            ps.setString(2, grade.getTestID());
            ps.setString(3, grade.getUserID());
            rows = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps, rs);
        }
        return rows;
    }
}
