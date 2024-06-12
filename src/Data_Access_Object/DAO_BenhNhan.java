package Data_Access_Object;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date; // Import Date class
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.BenhNhan;

public class DAO_BenhNhan implements Interface<BenhNhan> {
    public static DAO_BenhNhan getInstance() {
        return new DAO_BenhNhan();
    }

    @Override
    public int Add(BenhNhan t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "INSERT INTO BENHNHAN(HoTen, NgaySinh, GioiTinh, DiaChi, SDT, Image_BN, Status)"
                    + " VALUES ('" + t.getHoTen() + "', '"
                    + t.getNgaySinh() + "', '"
                    + t.getGioiTinh() + "', '"
                    + t.getDiaChi() + "', '"
                    + t.getSDT() + "', '"
                    + t.getImage_BN() + "', "
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
    public int Update(BenhNhan t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE BENHNHAN SET HoTen= '" + t.getHoTen() + "'" +
                    ", NgaySinh= '" + t.getNgaySinh() + "'" +
                    ", GioiTinh= '" + t.getGioiTinh() + "'" +
                    ", DiaChi= '" + t.getDiaChi() + "'" +
                    ", SDT= '" + t.getSDT() + "'" +
                    ", Image_BN= '" + t.getImage_BN() + "'" +
                    ", Status= " + t.getStatus() +
                    " WHERE MaBN= " + t.getMaBN();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int UpdateStatus(BenhNhan t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE BENHNHAN SET Status= " + t.getStatus() +
                    " WHERE MaBN= " + t.getMaBN();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int Delete(BenhNhan t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "DELETE FROM BENHNHAN WHERE MaBN= " + t.getMaBN();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ObservableList<BenhNhan> selectAll() {
        ObservableList<BenhNhan> resultList = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM BENHNHAN";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaBN = rs.getInt("MaBN");
                String HoTen = rs.getString("HoTen");
                Date NgaySinh = rs.getDate("NgaySinh");
                String GioiTinh = rs.getString("GioiTinh");
                String DiaChi = rs.getString("DiaChi");
                String SDT = rs.getString("SDT");
                String Image_BN = rs.getString("Image_BN");
                boolean Status = rs.getBoolean("Status");
                BenhNhan benhNhan = new BenhNhan(MaBN, HoTen, NgaySinh, GioiTinh, DiaChi, SDT, Image_BN, Status);
                resultList.add(benhNhan);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public BenhNhan seclectById(BenhNhan t) {
        BenhNhan result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM BENHNHAN WHERE MaBN=" + t.getMaBN();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int MaBN = rs.getInt("MaBN");
                String HoTen = rs.getString("HoTen");
                Date NgaySinh = rs.getDate("NgaySinh");
                String GioiTinh = rs.getString("GioiTinh");
                String DiaChi = rs.getString("DiaChi");
                String SDT = rs.getString("SDT");
                String Image_BN = rs.getString("Image_BN");
                boolean Status = rs.getBoolean("Status");
                result = new BenhNhan(MaBN, HoTen, NgaySinh, GioiTinh, DiaChi, SDT, Image_BN, Status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<BenhNhan> selectByCondition(String condition) {
        ArrayList<BenhNhan> resultList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM BENHNHAN WHERE " + condition;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaBN = rs.getInt("MaBN");
                String HoTen = rs.getString("HoTen");
                Date NgaySinh = rs.getDate("NgaySinh");
                String GioiTinh = rs.getString("GioiTinh");
                String DiaChi = rs.getString("DiaChi");
                String SDT = rs.getString("SDT");
                String Image_BN = rs.getString("Image_BN");
                boolean Status = rs.getBoolean("Status");
                BenhNhan benhNhan = new BenhNhan(MaBN, HoTen, NgaySinh, GioiTinh, DiaChi, SDT, Image_BN, Status);
                resultList.add(benhNhan);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
    
    public ObservableList<BenhNhan> selectByCondition2(String condition) {
    	ObservableList<BenhNhan> resultList = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM BENHNHAN WHERE " + condition;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaBN = rs.getInt("MaBN");
                String HoTen = rs.getString("HoTen");
                Date NgaySinh = rs.getDate("NgaySinh");
                String GioiTinh = rs.getString("GioiTinh");
                String DiaChi = rs.getString("DiaChi");
                String SDT = rs.getString("SDT");
                String Image_BN = rs.getString("Image_BN");
                boolean Status = rs.getBoolean("Status");
                BenhNhan benhNhan = new BenhNhan(MaBN, HoTen, NgaySinh, GioiTinh, DiaChi, SDT, Image_BN, Status);
                resultList.add(benhNhan);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
