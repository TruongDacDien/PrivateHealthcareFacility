package Data_Access_Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.LichHen;
import java.sql.Timestamp;

public class DAO_LichHen implements Interface<LichHen> {
    public static DAO_LichHen getInstance() {
        return new DAO_LichHen();
    }

    @Override
    public int Add(LichHen t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "INSERT INTO LICHHEN(MaBS, MaBN, ThoiGianHen, TrangThai, Status) VALUES (" + t.getMaBS() + ", " + t.getMaBN() + ", '" + t.getThoiGianHen() + "', '" + t.getTrangThai() + "', " + t.getStatus() + ")";
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
    public int Update(LichHen t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE LICHHEN SET MaBS= " + t.getMaBS() + ", MaBN= " + t.getMaBN() + ", ThoiGianHen= '" + t.getThoiGianHen() + "', TrangThai= '" + t.getTrangThai() + "', Status= " + t.getStatus() + " WHERE MaLH= " + t.getMaLH();
            int kq = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int UpdateStatus(LichHen t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE LICHHEN SET Status= " + t.getStatus() + " WHERE MaLH= " + t.getMaLH();
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
    public int Delete(LichHen t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "DELETE FROM LICHHEN WHERE MaLH= " + t.getMaLH();
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
    public ObservableList<LichHen> selectAll() {
        ObservableList<LichHen> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM LICHHEN";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaLH = rs.getInt("MaLH");
                int MaBS = rs.getInt("MaBS");
                int MaBN = rs.getInt("MaBN");
                Timestamp ThoiGianHen = rs.getTimestamp("ThoiGianHen");
                String TrangThai = rs.getString("TrangThai");
                boolean Status = rs.getBoolean("Status");
                LichHen lichhen = new LichHen(MaLH, MaBS, MaBN, ThoiGianHen, TrangThai, Status);
                kq.add(lichhen);
            }
            JDBCUtil.closeConnection(con);    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public LichHen seclectById(LichHen t) {
        LichHen kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM LICHHEN WHERE MaLH=" + t.getMaLH();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaLH = rs.getInt("MaLH");
                int MaBS = rs.getInt("MaBS");
                int MaBN = rs.getInt("MaBN");
                Timestamp ThoiGianHen = rs.getTimestamp("ThoiGianHen");
                String TrangThai = rs.getString("TrangThai");
                boolean Status = rs.getBoolean("Status");
                kq = new LichHen(MaLH, MaBS, MaBN, ThoiGianHen, TrangThai, Status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public ArrayList<LichHen> selectByCondition(String condition) {
        ArrayList<LichHen> kq = new ArrayList<>();
        
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM LICHHEN WHERE " + condition;
            System.out.println(sql);
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int MaLH = rs.getInt("MaLH");
                int MaBS = rs.getInt("MaBS");
                int MaBN = rs.getInt("MaBN");
                Timestamp ThoiGianHen = rs.getTimestamp("ThoiGianHen");
                String TrangThai = rs.getString("TrangThai");
                boolean Status = rs.getBoolean("Status");
                
                LichHen lichhen = new LichHen(MaLH, MaBS, MaBN, ThoiGianHen, TrangThai, Status);
                kq.add(lichhen);
            }
            
            JDBCUtil.closeConnection(con);    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return kq;
    }
    
    public ObservableList<LichHen> selectByCondition2(String condition) {
        ObservableList<LichHen> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM LICHHEN WHERE " + condition;
            System.out.println(sql);
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int MaLH = rs.getInt("MaLH");
                int MaBS = rs.getInt("MaBS");
                int MaBN = rs.getInt("MaBN");
                Timestamp ThoiGianHen = rs.getTimestamp("ThoiGianHen");
                String TrangThai = rs.getString("TrangThai");
                boolean Status = rs.getBoolean("Status");
                
                LichHen lichhen = new LichHen(MaLH, MaBS, MaBN, ThoiGianHen, TrangThai, Status);
                kq.add(lichhen);
            }
            
            JDBCUtil.closeConnection(con);    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return kq;
    }
}
