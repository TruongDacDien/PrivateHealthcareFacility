package Data_Access_Object;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.BaoCaoDoanhSo;

public class DAO_BaoCaoDoanhSo implements Interface<BaoCaoDoanhSo> {
    public static DAO_BaoCaoDoanhSo getInstance() {
        return new DAO_BaoCaoDoanhSo();
    }

    @Override
    public int Add(BaoCaoDoanhSo t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "INSERT INTO BAOCAODOANHSO(NgayLapDoanhSo, DoanhSo, SoLuongPhieuKham)"
                    + " VALUES ('" + t.getNgayLapDoanhSo() + "', "
                    + t.getDoanhSo() + ", "
                    + t.getSoLuongPhieuKham() + ")";
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int Update(BaoCaoDoanhSo t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE BAOCAODOANHSO SET NgayLapDoanhSo= '" + t.getNgayLapDoanhSo() + "'" +
                    ", DoanhSo= " + t.getDoanhSo() +
                    ", SoLuongPhieuKham= " + t.getSoLuongPhieuKham() +
                    " WHERE MaBCDoanhSo= " + t.getMaBCDoanhSo();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int Delete(BaoCaoDoanhSo t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "DELETE FROM BAOCAODOANHSO WHERE MaBCDoanhSo= " + t.getMaBCDoanhSo();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ObservableList<BaoCaoDoanhSo> selectAll() {
        ObservableList<BaoCaoDoanhSo> resultList = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM BAOCAODOANHSO";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaBCDoanhSo = rs.getInt("MaBCDoanhSo");
                Date NgayLapDoanhSo = rs.getDate("NgayLapDoanhSo");
                BigDecimal DoanhSo = rs.getBigDecimal("DoanhSo");
                int SoLuongPhieuKham = rs.getInt("SoLuongPhieuKham");
                BaoCaoDoanhSo doanhSo = new BaoCaoDoanhSo(MaBCDoanhSo, NgayLapDoanhSo, DoanhSo, SoLuongPhieuKham);
                resultList.add(doanhSo);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public BaoCaoDoanhSo seclectById(BaoCaoDoanhSo t) {
        BaoCaoDoanhSo result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM BAOCAODOANHSO WHERE MaBCDoanhSo=" + t.getMaBCDoanhSo();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int MaBCDoanhSo = rs.getInt("MaBCDoanhSo");
                Date NgayLapDoanhSo = rs.getDate("NgayLapDoanhSo");
                BigDecimal DoanhSo = rs.getBigDecimal("DoanhSo");
                int SoLuongPhieuKham = rs.getInt("SoLuongPhieuKham");
                result = new BaoCaoDoanhSo(MaBCDoanhSo, NgayLapDoanhSo, DoanhSo, SoLuongPhieuKham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<BaoCaoDoanhSo> selectByCondition(String condition) {
        ArrayList<BaoCaoDoanhSo> resultList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM BAOCAODOANHSO WHERE " + condition;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaBCDoanhSo = rs.getInt("MaBCDoanhSo");
                Date NgayLapDoanhSo = rs.getDate("NgayLapDoanhSo");
                BigDecimal DoanhSo = rs.getBigDecimal("DoanhSo");
                int SoLuongPhieuKham = rs.getInt("SoLuongPhieuKham");
                BaoCaoDoanhSo doanhSo = new BaoCaoDoanhSo(MaBCDoanhSo, NgayLapDoanhSo, DoanhSo, SoLuongPhieuKham);
                resultList.add(doanhSo);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
    
    public BaoCaoDoanhSo selectByCondition2(String condition) {
        BaoCaoDoanhSo resultList = new BaoCaoDoanhSo();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM BAOCAODOANHSO WHERE " + condition;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            System.out.print("LKDSJHLFJ");
            while (rs.next()) {
                int MaBCDoanhSo = rs.getInt("MaBCDoanhSo");
                Date NgayLapDoanhSo = rs.getDate("NgayLapDoanhSo");
                BigDecimal DoanhSo = rs.getBigDecimal("DoanhSo");
                int SoLuongPhieuKham = rs.getInt("SoLuongPhieuKham");
                BaoCaoDoanhSo doanhSo = new BaoCaoDoanhSo(MaBCDoanhSo, NgayLapDoanhSo, DoanhSo, SoLuongPhieuKham);
                
                resultList= doanhSo;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
