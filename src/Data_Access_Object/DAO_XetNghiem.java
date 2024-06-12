package Data_Access_Object;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.XetNghiem;

public class DAO_XetNghiem implements Interface<XetNghiem> {
    public static DAO_XetNghiem getInstance() {
        return new DAO_XetNghiem();
    }

    @Override
    public int Add(XetNghiem t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();

            String sql = "INSERT INTO XETNGHIEM(MaPK, MaLoaiXN, MaBSXN, TenXN, KetQua, ChuanDoan, ChiPhiXN, Status) VALUES ("
                    + t.getMaPK() + ", " + t.getMaLoaiXN() + ", " + t.getMaBSXN() + ", '" + t.getTenXN() + "', '"
                    + t.getKetQua() + "', '" + t.getChuanDoan() + "', " + t.getChiPhiXN() + ", " + t.getStatus() + ")";
            int kq = st.executeUpdate(sql);

            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);

            return kq; // return the number of affected rows

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int AddAndGetID(XetNghiem xetNghiem) {
        int generatedId = -1;
        String sql = "INSERT INTO XETNGHIEM(MaPK, MaLoaiXN, MaBSXN, TenXN, KetQua, ChuanDoan, ChiPhiXN, Status) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, xetNghiem.getMaPK());
            preparedStatement.setInt(2, xetNghiem.getMaLoaiXN());
            preparedStatement.setInt(3, xetNghiem.getMaBSXN());
            preparedStatement.setString(4, xetNghiem.getTenXN());
            preparedStatement.setString(5, xetNghiem.getKetQua());
            preparedStatement.setString(6, xetNghiem.getChuanDoan());
            preparedStatement.setBigDecimal(7, xetNghiem.getChiPhiXN());
            preparedStatement.setBoolean(8, xetNghiem.getStatus());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    @Override
    public int Update(XetNghiem t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();

            String sql = "UPDATE XETNGHIEM SET MaPK= " + t.getMaPK() + ", MaLoaiXN= " + t.getMaLoaiXN() + ", MaBSXN= "
                    + t.getMaBSXN() + ", TenXN= '" + t.getTenXN() + "', KetQua= '" + t.getKetQua() + "', ChuanDoan= '"
                    + t.getChuanDoan() + "', ChiPhiXN= " + t.getChiPhiXN() + ", Status= " + t.getStatus() 
                    + " WHERE MaXN= " + t.getMaXN();
            int kq = st.executeUpdate(sql);

            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);

            return kq; // return the number of affected rows

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    public int UpdateStatus(XetNghiem t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();

            String sql = "UPDATE XETNGHIEM SET Status= " + t.getStatus() 
                    + " WHERE MaXN= " + t.getMaXN();
            int kq = st.executeUpdate(sql);

            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);

            return kq; // return the number of affected rows

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    @Override
    public int Delete(XetNghiem t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();

            String sql = "DELETE FROM XETNGHIEM WHERE MaXN= " + t.getMaXN();
            int kq = st.executeUpdate(sql);

            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);

            return kq; // return the number of affected rows

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public ObservableList<XetNghiem> selectAll() {
        ObservableList<XetNghiem> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM XETNGHIEM";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int MaXN = rs.getInt("MaXN");
                int MaPK = rs.getInt("MaPK");
                int MaLoaiXN = rs.getInt("MaLoaiXN");
                int MaBSXN = rs.getInt("MaBSXN");
                String TenXN = rs.getString("TenXN");
                String KetQua = rs.getString("KetQua");
                String ChuanDoan = rs.getString("ChuanDoan");
                BigDecimal ChiPhiXN = rs.getBigDecimal("ChiPhiXN");
                boolean Status = rs.getBoolean("Status");

                XetNghiem xetNghiem = new XetNghiem(MaXN, MaPK, MaLoaiXN, MaBSXN, TenXN, KetQua, ChuanDoan, ChiPhiXN, Status);
                kq.add(xetNghiem);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kq;
    }

    @Override
    public XetNghiem seclectById(XetNghiem t) {
        XetNghiem kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM XETNGHIEM WHERE MaXN=" + t.getMaXN();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int MaXN = rs.getInt("MaXN");
                int MaPK = rs.getInt("MaPK");
                int MaLoaiXN = rs.getInt("MaLoaiXN");
                int MaBSXN = rs.getInt("MaBSXN");
                String TenXN = rs.getString("TenXN");
                String KetQua = rs.getString("KetQua");
                String ChuanDoan = rs.getString("ChuanDoan");
                BigDecimal ChiPhiXN = rs.getBigDecimal("ChiPhiXN");
                boolean Status = rs.getBoolean("Status");

                kq = new XetNghiem(MaXN, MaPK, MaLoaiXN, MaBSXN, TenXN, KetQua, ChuanDoan, ChiPhiXN, Status);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kq;
    }

    @Override
    public ArrayList<XetNghiem> selectByCondition(String condition) {
        ArrayList<XetNghiem> kq = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM XETNGHIEM WHERE " + condition;
            System.out.println(sql);

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int MaXN = rs.getInt("MaXN");
                int MaPK = rs.getInt("MaPK");
                int MaLoaiXN = rs.getInt("MaLoaiXN");
                int MaBSXN = rs.getInt("MaBSXN");
                String TenXN = rs.getString("TenXN");
                String KetQua = rs.getString("KetQua");
                String ChuanDoan = rs.getString("ChuanDoan");
                BigDecimal ChiPhiXN = rs.getBigDecimal("ChiPhiXN");
                boolean Status = rs.getBoolean("Status");

                XetNghiem xetNghiem = new XetNghiem(MaXN, MaPK, MaLoaiXN, MaBSXN, TenXN, KetQua, ChuanDoan, ChiPhiXN, Status);
                kq.add(xetNghiem);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kq;
    }

    public ObservableList<XetNghiem> selectByCondition2(String condition) {
        ObservableList<XetNghiem> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM XETNGHIEM WHERE " + condition;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int MaXN = rs.getInt("MaXN");
                int MaPK = rs.getInt("MaPK");
                int MaLoaiXN = rs.getInt("MaLoaiXN");
                int MaBSXN = rs.getInt("MaBSXN");
                String TenXN = rs.getString("TenXN");
                String KetQua = rs.getString("KetQua");
                String ChuanDoan = rs.getString("ChuanDoan");
                BigDecimal ChiPhiXN = rs.getBigDecimal("ChiPhiXN");
                boolean Status = rs.getBoolean("Status");

                XetNghiem xetNghiem = new XetNghiem(MaXN, MaPK, MaLoaiXN, MaBSXN, TenXN, KetQua, ChuanDoan, ChiPhiXN, Status);
                kq.add(xetNghiem);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kq;
    }
}