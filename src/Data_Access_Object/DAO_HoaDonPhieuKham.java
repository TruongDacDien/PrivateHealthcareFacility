package Data_Access_Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.HoaDonPhieuKham;

public class DAO_HoaDonPhieuKham implements Interface<HoaDonPhieuKham> {
    public static DAO_HoaDonPhieuKham getInstance() {
        return new DAO_HoaDonPhieuKham();
    }
    
    @Override
    public int Add(HoaDonPhieuKham t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "INSERT INTO HOADONPHIEUKHAM(MaPK, NgayBan, TongTien, isPaid) VALUES ("+ t.getMaPK() + ", '" + t.getNgayBan() + "', " + t.getTongTien() + ", " + t.getIsPaid() + ")";
            int kq = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Update(HoaDonPhieuKham t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE HOADONPHIEUKHAM SET MaPK= " + t.getMaPK() + ", NgayBan= '" + t.getNgayBan() + "', TongTien= " + t.getTongTien() + ", isPaid= " + t.getIsPaid() + " WHERE MaHD= " + t.getMaHD();
            int kq = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Delete(HoaDonPhieuKham t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "DELETE FROM HOADONPHIEUKHAM WHERE MaHD= " + t.getMaHD();
            int kq = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ObservableList<HoaDonPhieuKham> selectAll() {
        ObservableList<HoaDonPhieuKham> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM HOADONPHIEUKHAM";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaHD = rs.getInt("MaHD");
                int MaPK = rs.getInt("MaPK");
                java.sql.Date NgayBan = rs.getDate("NgayBan");
                java.math.BigDecimal TongTien = rs.getBigDecimal("TongTien");
                boolean isPaid = rs.getBoolean("isPaid");
                HoaDonPhieuKham hoadonPhieuKham = new HoaDonPhieuKham(MaHD, MaPK, NgayBan, TongTien, isPaid);
                kq.add(hoadonPhieuKham);
            }
            JDBCUtil.closeConnection(con);    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public HoaDonPhieuKham seclectById(HoaDonPhieuKham t) {
        HoaDonPhieuKham kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM HOADONPHIEUKHAM WHERE MaHD=" + t.getMaHD();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaHD = rs.getInt("MaHD");
                int MaPK = rs.getInt("MaPK");
                java.sql.Date NgayBan = rs.getDate("NgayBan");
                java.math.BigDecimal TongTien = rs.getBigDecimal("TongTien");
                boolean isPaid = rs.getBoolean("isPaid");
                kq = new HoaDonPhieuKham(MaHD, MaPK, NgayBan, TongTien, isPaid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    public ArrayList<HoaDonPhieuKham> selectByCondition(String condition) {
ArrayList<HoaDonPhieuKham> kq = new ArrayList<HoaDonPhieuKham>();
        
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM HOADONPHIEUKHAM WHERE " + condition;
            System.out.println(sql); 
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int MaHD = rs.getInt("MaHD");
                int MaPK = rs.getInt("MaPK");
                java.sql.Date NgayBan = rs.getDate("NgayBan");
                java.math.BigDecimal TongTien = rs.getBigDecimal("TongTien");
                boolean isPaid = rs.getBoolean("isPaid");
                HoaDonPhieuKham hoadonPhieuKham = new HoaDonPhieuKham(MaHD, MaPK, NgayBan, TongTien, isPaid);
                kq.add(hoadonPhieuKham);
            }
            
            JDBCUtil.closeConnection(con);     
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return kq;
        
       
    }
    
    public HoaDonPhieuKham selectByCondition2(String condition) {
        HoaDonPhieuKham kq = new HoaDonPhieuKham();
        
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM HOADONPHIEUKHAM WHERE " + condition;
            System.out.println(sql); 
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int MaHD = rs.getInt("MaHD");
                int MaPK = rs.getInt("MaPK");
                java.sql.Date NgayBan = rs.getDate("NgayBan");
                java.math.BigDecimal TongTien = rs.getBigDecimal("TongTien");
                boolean isPaid = rs.getBoolean("isPaid");
                HoaDonPhieuKham hoadonPhieuKham = new HoaDonPhieuKham(MaHD, MaPK, NgayBan, TongTien, isPaid);
                kq = hoadonPhieuKham;
            }
            
            JDBCUtil.closeConnection(con);     
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return kq;
    }
}
