package Data_Access_Object;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.BacSi;

public class DAO_BacSi implements Interface<BacSi> {
    public static DAO_BacSi getInstance() {
        return new DAO_BacSi();
    }

    @Override
    public int Add(BacSi t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "INSERT INTO BACSI(HoTen, ChuyenMon, DiaChi, SDT, GioiTinh, Email, NamKN, Image_BS, Status)"
                    + " VALUES ('" + t.getHoTen() + "' , '"
                    + t.getChuyenMon() + "' , '"
                    + t.getDiaChi() + "' , '"
                    + t.getSDT() + "' , '"
                    + t.getGioiTinh() + "' , '"
                    + t.getEmail() + "' , '"
                    + t.getNamKN() + "' , '"
                    + t.getImage_BS() + "', "
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
    public int Update(BacSi t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE BACSI SET HoTen= '" + t.getHoTen() + "'" +
                         ", ChuyenMon= '" + t.getChuyenMon() + "'" +
                         ", DiaChi= '" + t.getDiaChi() + "'" +
                         ", SDT= '" + t.getSDT() + "'" +
                         ", GioiTinh= '" + t.getGioiTinh() + "'" +
                         ", Email= '" + t.getEmail() + "'" +
                         ", NamKN= '" + t.getNamKN() + "'" +
                         ", Image_BS= '" + t.getImage_BS() + "'" +
                         ", Status= " + t.getStatus() +
                         " WHERE MaBS= '" + t.getMaBS() + "'";
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int UpdateStatus(BacSi t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE BACSI SET Status= " + t.getStatus() +
                         " WHERE MaBS= '" + t.getMaBS() + "'";
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int Delete(BacSi t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "DELETE FROM BACSI WHERE MaBS= " + t.getMaBS();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ObservableList<BacSi> selectAll() {
        ObservableList<BacSi> resultList = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM BACSI";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaBS = rs.getInt("MaBS");
                String HoTen = rs.getString("HoTen");
                String ChuyenMon = rs.getString("ChuyenMon");
                String DiaChi = rs.getString("DiaChi");
                String SDT = rs.getString("SDT");
                String GioiTinh = rs.getString("GioiTinh");
                String Email = rs.getString("Email");
                int NamKN = rs.getInt("NamKN");
                String Image_BS = rs.getString("Image_BS");
                boolean Status = rs.getBoolean("Status");
                BacSi bacSi = new BacSi(MaBS, HoTen, ChuyenMon, DiaChi, SDT, GioiTinh, Email, NamKN, Image_BS, Status);
                resultList.add(bacSi);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public BacSi seclectById(BacSi t) {
        BacSi result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM BACSI WHERE MaBS=" + t.getMaBS();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int MaBS = rs.getInt("MaBS");
                String HoTen = rs.getString("HoTen");
                String ChuyenMon = rs.getString("ChuyenMon");
                String DiaChi = rs.getString("DiaChi");
                String SDT = rs.getString("SDT");
                String GioiTinh = rs.getString("GioiTinh");
                String Email = rs.getString("Email");
                int NamKN = rs.getInt("NamKN");
                String Image_BS = rs.getString("Image_BS");
                boolean Status = rs.getBoolean("Status");
                result = new BacSi(MaBS, HoTen, ChuyenMon, DiaChi, SDT, GioiTinh, Email, NamKN, Image_BS, Status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<BacSi> selectByCondition(String condition) {
        ArrayList<BacSi> resultList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM BACSI WHERE " + condition;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaBS = rs.getInt("MaBS");
                String HoTen = rs.getString("HoTen");
                String ChuyenMon = rs.getString("ChuyenMon");
                String DiaChi = rs.getString("DiaChi");
                String SDT = rs.getString("SDT");
                String GioiTinh = rs.getString("GioiTinh");
                String Email = rs.getString("Email");
                int NamKN = rs.getInt("NamKN");
                String Image_BS = rs.getString("Image_BS");
                boolean Status = rs.getBoolean("Status");
                BacSi bacSi = new BacSi(MaBS, HoTen, ChuyenMon, DiaChi, SDT, GioiTinh, Email, NamKN, Image_BS, Status);
                resultList.add(bacSi);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
    
    public ObservableList<BacSi> selectByCondition2(String condition) {
        ObservableList<BacSi> resultList = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM BACSI WHERE " + condition;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaBS = rs.getInt("MaBS");
                String HoTen = rs.getString("HoTen");
                String ChuyenMon = rs.getString("ChuyenMon");
                String DiaChi = rs.getString("DiaChi");
                String SDT = rs.getString("SDT");
                String GioiTinh = rs.getString("GioiTinh");
                String Email = rs.getString("Email");
                int NamKN = rs.getInt("NamKN");
                String Image_BS = rs.getString("Image_BS");
                boolean Status = rs.getBoolean("Status");
                BacSi bacSi = new BacSi(MaBS, HoTen, ChuyenMon, DiaChi, SDT, GioiTinh, Email, NamKN, Image_BS, Status);
                resultList.add(bacSi);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
