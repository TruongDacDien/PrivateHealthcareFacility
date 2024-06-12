package Data_Access_Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Thuoc;

public class DAO_Thuoc implements Interface<Thuoc> {
    public static DAO_Thuoc getInstance() {
        return new DAO_Thuoc();
    }

    @Override
    public int Add(Thuoc t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "INSERT INTO THUOC(TenThuoc, DonVi, SoLuong, DonGia, NgaySX, HanSuDung, Status) VALUES ('"
                    + t.getTenThuoc() + "', '" + t.getDonVi() + "', " + t.getSoLuong() + ", " + t.getDonGia() + ", '"
                    + t.getNgaySX() + "', '" + t.getHanSuDung() + "', " + t.getStatus() + ")";
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
    public int Update(Thuoc t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE THUOC SET TenThuoc= '" + t.getTenThuoc() + "', DonVi= '" + t.getDonVi() + "', SoLuong= "
                    + t.getSoLuong() + ", DonGia= " + t.getDonGia() + ", NgaySX= '" + t.getNgaySX() + "', HanSuDung= '"
                    + t.getHanSuDung() + "', Status= " + t.getStatus() + " WHERE MaThuoc= " + t.getMaThuoc();
            int kq = st.executeUpdate(sql);

            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int UpdateStatus(Thuoc t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE THUOC SET Status= " + t.getStatus() + " WHERE MaThuoc= " + t.getMaThuoc();
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
    public int Delete(Thuoc t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "DELETE from THUOC WHERE MaThuoc= " + t.getMaThuoc();
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
    public ObservableList<Thuoc> selectAll() {
        ObservableList<Thuoc> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM THUOC";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int MaThuoc = rs.getInt("MaThuoc");
                String TenThuoc = rs.getString("TenThuoc");
                String DonVi = rs.getString("DonVi");
                int SoLuong = rs.getInt("SoLuong");
                BigDecimal DonGia = rs.getBigDecimal("DonGia");
                Date NgaySX = rs.getDate("NgaySX");
                Date HanSuDung = rs.getDate("HanSuDung");
                boolean Status = rs.getBoolean("Status");

                Thuoc thuoc = new Thuoc(MaThuoc, TenThuoc, DonVi, SoLuong, DonGia, NgaySX, HanSuDung, Status);
                kq.add(thuoc);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public Thuoc seclectById(Thuoc t) {
        Thuoc kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM THUOC where MaThuoc=" + t.getMaThuoc();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int MaThuoc = rs.getInt("MaThuoc");
                String TenThuoc = rs.getString("TenThuoc");
                String DonVi = rs.getString("DonVi");
                int SoLuong = rs.getInt("SoLuong");
                BigDecimal DonGia = rs.getBigDecimal("DonGia");
                Date NgaySX = rs.getDate("NgaySX");
                Date HanSuDung = rs.getDate("HanSuDung");
                boolean Status = rs.getBoolean("Status");

                kq = new Thuoc(MaThuoc, TenThuoc, DonVi, SoLuong, DonGia, NgaySX, HanSuDung, Status);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    public Thuoc seclectById(int id) {
        Thuoc kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM THUOC where MaThuoc=" + id;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int MaThuoc = rs.getInt("MaThuoc");
                String TenThuoc = rs.getString("TenThuoc");
                String DonVi = rs.getString("DonVi");
                int SoLuong = rs.getInt("SoLuong");
                BigDecimal DonGia = rs.getBigDecimal("DonGia");
                Date NgaySX = rs.getDate("NgaySX");
                Date HanSuDung = rs.getDate("HanSuDung");
                boolean Status = rs.getBoolean("Status");

                kq = new Thuoc(MaThuoc, TenThuoc, DonVi, SoLuong, DonGia, NgaySX, HanSuDung, Status);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public ArrayList<Thuoc> selectByCondition(String condition) {
        ArrayList<Thuoc> kq = new ArrayList<>();

        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM THUOC WHERE " + condition;
            System.out.println(sql);

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int MaThuoc = rs.getInt("MaThuoc");
                String TenThuoc = rs.getString("TenThuoc");
                String DonVi = rs.getString("DonVi");
                int SoLuong = rs.getInt("SoLuong");
                BigDecimal DonGia = rs.getBigDecimal("DonGia");
                Date NgaySX = rs.getDate("NgaySX");
                Date HanSuDung = rs.getDate("HanSuDung");
                boolean Status = rs.getBoolean("Status");

                Thuoc thuoc = new Thuoc(MaThuoc, TenThuoc, DonVi, SoLuong, DonGia, NgaySX, HanSuDung, Status);
                kq.add(thuoc);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kq;
    }
    
    public ObservableList<Thuoc> selectByCondition2(String condition) {
    	ObservableList<Thuoc> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM THUOC WHERE " + condition;
            System.out.println(sql);

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int MaThuoc = rs.getInt("MaThuoc");
                String TenThuoc = rs.getString("TenThuoc");
                String DonVi = rs.getString("DonVi");
                int SoLuong = rs.getInt("SoLuong");
                BigDecimal DonGia = rs.getBigDecimal("DonGia");
                Date NgaySX = rs.getDate("NgaySX");
                Date HanSuDung = rs.getDate("HanSuDung");
                boolean Status = rs.getBoolean("Status");

                Thuoc thuoc = new Thuoc(MaThuoc, TenThuoc, DonVi, SoLuong, DonGia, NgaySX, HanSuDung, Status);
                kq.add(thuoc);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kq;
    }
}
