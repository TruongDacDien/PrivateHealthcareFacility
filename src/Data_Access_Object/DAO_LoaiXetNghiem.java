package Data_Access_Object;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.LoaiXetNghiem;

public class DAO_LoaiXetNghiem implements Interface<LoaiXetNghiem> {
    public static DAO_LoaiXetNghiem getInstance() {
        return new DAO_LoaiXetNghiem();
    }
    
    @Override
    public int Add(LoaiXetNghiem t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "INSERT INTO LOAIXETNGHIEM(TenLoaiXN, ChiPhiXN, MoTaXN, Status) VALUES ('" + t.getTenLoaiXN() + "', " + t.getChiPhiXN() + ", '" + t.getMoTaXN() + "', " + t.isStatus() + ")";
            int kq = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int Update(LoaiXetNghiem t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE LOAIXETNGHIEM SET TenLoaiXN= '" + t.getTenLoaiXN() + "', ChiPhiXN= " + t.getChiPhiXN() + ", MoTaXN= '" + t.getMoTaXN() + "', Status= " + t.isStatus() + " WHERE MaLoaiXN= " + t.getMaLoaiXN();
            int kq = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int UpdateStatus(LoaiXetNghiem t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE LOAIXETNGHIEM SET Status= " + t.isStatus() + " WHERE MaLoaiXN= " + t.getMaLoaiXN();
            int kq = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int Delete(LoaiXetNghiem t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "DELETE FROM LOAIXETNGHIEM WHERE MaLoaiXN= " + t.getMaLoaiXN();
            int kq = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ObservableList<LoaiXetNghiem> selectAll() {
        ObservableList<LoaiXetNghiem> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM LOAIXETNGHIEM";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaLoaiXN = rs.getInt("MaLoaiXN");
                String TenLoaiXN = rs.getString("TenLoaiXN");
                BigDecimal ChiPhiXN = rs.getBigDecimal("ChiPhiXN");
                String MoTaXN = rs.getString("MoTaXN");
                boolean Status = rs.getBoolean("Status");
                LoaiXetNghiem loaiXetNghiem = new LoaiXetNghiem(MaLoaiXN, TenLoaiXN, ChiPhiXN, MoTaXN, Status);
                kq.add(loaiXetNghiem);
            }
            JDBCUtil.closeConnection(con);    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public LoaiXetNghiem seclectById(LoaiXetNghiem t) {
        LoaiXetNghiem kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM LOAIXETNGHIEM WHERE MaLoaiXN=" + t.getMaLoaiXN();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaLoaiXN = rs.getInt("MaLoaiXN");
                String TenLoaiXN = rs.getString("TenLoaiXN");
                BigDecimal ChiPhiXN = rs.getBigDecimal("ChiPhiXN");
                String MoTaXN = rs.getString("MoTaXN");
                boolean Status = rs.getBoolean("Status");
                kq = new LoaiXetNghiem(MaLoaiXN, TenLoaiXN, ChiPhiXN, MoTaXN, Status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    @Override
    public ArrayList<LoaiXetNghiem> selectByCondition(String condition) {
        ArrayList<LoaiXetNghiem> kq = new ArrayList<>();
        
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM LOAIXETNGHIEM WHERE " + condition;
            System.out.println(sql); 
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int MaLoaiXN = rs.getInt("MaLoaiXN");
                String TenLoaiXN = rs.getString("TenLoaiXN");
                BigDecimal ChiPhiXN = rs.getBigDecimal("ChiPhiXN");
                String MoTaXN = rs.getString("MoTaXN");
                boolean Status = rs.getBoolean("Status");
                
                LoaiXetNghiem loaiXetNghiem = new LoaiXetNghiem(MaLoaiXN, TenLoaiXN, ChiPhiXN, MoTaXN, Status);
                kq.add(loaiXetNghiem);
            }
            
            JDBCUtil.closeConnection(con);    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return kq;
    }
    
    public ObservableList<LoaiXetNghiem> selectByCondition2(String condition) {
    	ObservableList<LoaiXetNghiem> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM LOAIXETNGHIEM WHERE " + condition;
            System.out.println(sql); 
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int MaLoaiXN = rs.getInt("MaLoaiXN");
                String TenLoaiXN = rs.getString("TenLoaiXN");
                BigDecimal ChiPhiXN = rs.getBigDecimal("ChiPhiXN");
                String MoTaXN = rs.getString("MoTaXN");
                boolean Status = rs.getBoolean("Status");
                
                LoaiXetNghiem loaiXetNghiem = new LoaiXetNghiem(MaLoaiXN, TenLoaiXN, ChiPhiXN, MoTaXN, Status);
                kq.add(loaiXetNghiem);
            }
            
            JDBCUtil.closeConnection(con);    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return kq;
    }
}
