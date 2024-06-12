package Data_Access_Object;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.BaoCaoSuDungThuoc;

public class DAO_BaoCaoSuDungThuoc implements Interface<BaoCaoSuDungThuoc> {
    public static DAO_BaoCaoSuDungThuoc getInstance() {
        return new DAO_BaoCaoSuDungThuoc();
    }

    @Override
    public int Add(BaoCaoSuDungThuoc t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "INSERT INTO BAOCAOSUDUNGTHUOC(NgayLapThuoc, MaThuoc, SoLuongSuDung, SoLuongConLai)"
                    + " VALUES ('" + t.getNgayLapThuoc() + "', "
                    + t.getMaThuoc() + ", "
                    + t.getSoLuongSuDung() + ", "
                    + t.getSoLuongConLai() + ")";
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int Update(BaoCaoSuDungThuoc t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE BAOCAOSUDUNGTHUOC SET NgayLapThuoc= '" + t.getNgayLapThuoc() + "'" +
                    ", MaThuoc= " + t.getMaThuoc() +
                    ", SoLuongSuDung= " + t.getSoLuongSuDung() +
                    ", SoLuongConLai= " + t.getSoLuongConLai() +
                    " WHERE MaBCThuoc= " + t.getMaBCThuoc();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int Delete(BaoCaoSuDungThuoc t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "DELETE FROM BAOCAOSUDUNGTHUOC WHERE MaBCThuoc= " + t.getMaBCThuoc();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ObservableList<BaoCaoSuDungThuoc> selectAll() {
        ObservableList<BaoCaoSuDungThuoc> resultList = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM BAOCAOSUDUNGTHUOC";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaBCThuoc = rs.getInt("MaBCThuoc");
                Date NgayLapThuoc = rs.getDate("NgayLapThuoc");
                int MaThuoc = rs.getInt("MaThuoc");
                int SoLuongSuDung = rs.getInt("SoLuongSuDung");
                int SoLuongConLai = rs.getInt("SoLuongConLai");
                BaoCaoSuDungThuoc baocaoSuDungThuoc = new BaoCaoSuDungThuoc(MaBCThuoc, NgayLapThuoc, MaThuoc, SoLuongSuDung, SoLuongConLai);
                resultList.add(baocaoSuDungThuoc);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public BaoCaoSuDungThuoc seclectById(BaoCaoSuDungThuoc t) {
        BaoCaoSuDungThuoc result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM BAOCAOSUDUNGTHUOC WHERE MaBCThuoc=" + t.getMaBCThuoc();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int MaBCThuoc = rs.getInt("MaBCThuoc");
                Date NgayLapThuoc = rs.getDate("NgayLapThuoc");
                int MaThuoc = rs.getInt("MaThuoc");
                int SoLuongSuDung = rs.getInt("SoLuongSuDung");
                int SoLuongConLai = rs.getInt("SoLuongConLai");
                result = new BaoCaoSuDungThuoc(MaBCThuoc, NgayLapThuoc, MaThuoc, SoLuongSuDung, SoLuongConLai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<BaoCaoSuDungThuoc> selectByCondition(String condition) {
        ArrayList<BaoCaoSuDungThuoc> resultList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM BAOCAOSUDUNGTHUOC WHERE " + condition;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MaBCThuoc = rs.getInt("MaBCThuoc");
                Date NgayLapThuoc = rs.getDate("NgayLapThuoc");
                int MaThuoc = rs.getInt("MaThuoc");
                int SoLuongSuDung = rs.getInt("SoLuongSuDung");
                int SoLuongConLai = rs.getInt("SoLuongConLai");
                BaoCaoSuDungThuoc baocaoSuDungThuoc = new BaoCaoSuDungThuoc(MaBCThuoc, NgayLapThuoc, MaThuoc, SoLuongSuDung, SoLuongConLai);
	            resultList.add(baocaoSuDungThuoc);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return resultList;
	}
	
}
