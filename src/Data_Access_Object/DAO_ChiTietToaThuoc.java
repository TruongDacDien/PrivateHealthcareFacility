package Data_Access_Object;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.math.BigDecimal;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.ChiTietToaThuoc;

public class DAO_ChiTietToaThuoc implements Interface<ChiTietToaThuoc> {
    public static DAO_ChiTietToaThuoc getInstance() {
        return new DAO_ChiTietToaThuoc();
    }

    @Override
    public int Add(ChiTietToaThuoc t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "INSERT INTO CHITIETOATHUOC(MaToa, MaThuoc, TenThuoc, SoLuong, KhoangThoiGian, LieuLuong, Tien, Status)"
                    + " VALUES (" + t.getMaToa() + " , "
                    + t.getMaThuoc() + " , '"
                    + t.getTenThuoc() + "' , "
                    + t.getSoLuong() + " , '"
                    + t.getKhoangThoiGian() + "' , '"
                    + t.getLieuLuong() + "' , "
                    + t.getTien() + ", "
                    + t.getStatus() + ")";
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int Update(ChiTietToaThuoc t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE CHITIETOATHUOC SET MaToa= " + t.getMaToa() +
                    ", MaThuoc= " + t.getMaThuoc() +
                    ", TenThuoc= '" + t.getTenThuoc() + "'" +
                    ", SoLuong= " + t.getSoLuong() +
                    ", KhoangThoiGian= '" + t.getKhoangThoiGian() + "'" +
                    ", LieuLuong= '" + t.getLieuLuong() + "'" +
                    ", Tien= " + t.getTien() +
                    ", Status= " + t.getStatus() +
                    " WHERE MaChiTietToa= " + t.getMaChiTietToa();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int UpdateStatus(ChiTietToaThuoc t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE CHITIETOATHUOC set Status= " + t.getStatus() +
                    " WHERE MaChiTietToa= " + t.getMaChiTietToa();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int Delete(ChiTietToaThuoc t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "DELETE FROM CHITIETOATHUOC WHERE MaChiTietToa= " + t.getMaChiTietToa();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ObservableList<ChiTietToaThuoc> selectAll() {
        ObservableList<ChiTietToaThuoc> resultList = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM CHITIETOATHUOC";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaChiTietToa = rs.getInt("MaChiTietToa");
                int MaToa = rs.getInt("MaToa");
                int MaThuoc = rs.getInt("MaThuoc");
                String TenThuoc = rs.getString("TenThuoc");
                int SoLuong = rs.getInt("SoLuong");
                String KhoangThoiGian = rs.getString("KhoangThoiGian");
                String LieuLuong = rs.getString("LieuLuong");
                BigDecimal Tien = rs.getBigDecimal("Tien");
                boolean Status = rs.getBoolean("Status");
                ChiTietToaThuoc chitietToaThuoc = new ChiTietToaThuoc(MaChiTietToa, MaToa, MaThuoc, TenThuoc, SoLuong, KhoangThoiGian, LieuLuong, Tien, Status);
                resultList.add(chitietToaThuoc);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public ChiTietToaThuoc seclectById(ChiTietToaThuoc t) {
        ChiTietToaThuoc result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM CHITIETOATHUOC WHERE MaChiTietToa=" + t.getMaChiTietToa();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int MaChiTietToa = rs.getInt("MaChiTietToa");
                int MaToa = rs.getInt("MaToa");
                int MaThuoc = rs.getInt("MaThuoc");
                String TenThuoc = rs.getString("TenThuoc");
                int SoLuong = rs.getInt("SoLuong");
                String KhoangThoiGian = rs.getString("KhoangThoiGian");
                String LieuLuong = rs.getString("LieuLuong");
                BigDecimal Tien = rs.getBigDecimal("Tien");
                boolean Status = rs.getBoolean("Status");
                result = new ChiTietToaThuoc(MaChiTietToa, MaToa, MaThuoc, TenThuoc, SoLuong, KhoangThoiGian, LieuLuong, Tien, Status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietToaThuoc> selectByCondition(String condition) {
        ArrayList<ChiTietToaThuoc> resultList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM CHITIETOATHUOC WHERE " + condition;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaChiTietToa = rs.getInt("MaChiTietToa");
                int MaToa = rs.getInt("MaToa");
                int MaThuoc = rs.getInt("MaThuoc");
                String TenThuoc = rs.getString("TenThuoc");
                int SoLuong = rs.getInt("SoLuong");
                String KhoangThoiGian = rs.getString("KhoangThoiGian");
                String LieuLuong = rs.getString("LieuLuong");
                BigDecimal Tien = rs.getBigDecimal("Tien");
                boolean Status = rs.getBoolean("Status");
                ChiTietToaThuoc chitietToaThuoc = new ChiTietToaThuoc(MaChiTietToa, MaToa, MaThuoc, TenThuoc, SoLuong, KhoangThoiGian, LieuLuong, Tien, Status);
                resultList.add(chitietToaThuoc);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    // Add this method for your second selectByCondition method
    public ObservableList<ChiTietToaThuoc> selectByCondition2(String condition) {
        ObservableList<ChiTietToaThuoc> resultList = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM CHITIETOATHUOC WHERE " + condition;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaChiTietToa = rs.getInt("MaChiTietToa");
                int MaToa = rs.getInt("MaToa");
                int MaThuoc = rs.getInt("MaThuoc");
                String TenThuoc = rs.getString("TenThuoc");
                int SoLuong = rs.getInt("SoLuong");
                String KhoangThoiGian = rs.getString("KhoangThoiGian");
                String LieuLuong = rs.getString("LieuLuong");
                BigDecimal Tien = rs.getBigDecimal("Tien");
                boolean Status = rs.getBoolean("Status");
                ChiTietToaThuoc chiTietToaThuoc = new ChiTietToaThuoc(MaChiTietToa, MaToa, MaThuoc, TenThuoc, SoLuong, KhoangThoiGian, LieuLuong, Tien, Status);
                resultList.add(chiTietToaThuoc);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}

