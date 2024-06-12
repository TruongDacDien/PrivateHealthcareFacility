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
import models.ToaThuoc;

public class DAO_ToaThuoc implements Interface<ToaThuoc> {
    public static DAO_ToaThuoc getInstance() {
        return new DAO_ToaThuoc();
    }
    
    @Override
    public int Add(ToaThuoc t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            
            Statement st = connect.createStatement();
            
            String sql = "INSERT INTO TOATHUOC(MaPK, NgayKeToa, TongTien)"
                    + "VALUES ("+ t.getMaPK() + " , '" 
                    + t.getNgayKeToa() + "' , " 
                    + t.getTongTien() + ")";
            int kq = st.executeUpdate(sql);
            
            System.out.println("Bạn đã thực thi");
            JDBCUtil.closeConnection(connect);
            
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int Update(ToaThuoc t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            
            Statement st = connect.createStatement();
            
            String sql = "UPDATE TOATHUOC "+
                         " SET "+
                         " MaPK= " + t.getMaPK() + "" +
                         ", NgayKeToa= '" + t.getNgayKeToa() + "'" +
                         ", TongTien= " + t.getTongTien() + "" +  
                         " WHERE MaToa= " + t.getMaToa();
            int kq = st.executeUpdate(sql);
            
            System.out.println("Bạn đã thực thi");
            JDBCUtil.closeConnection(connect);
            
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int Delete(ToaThuoc t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            
            Statement st = connect.createStatement();
            
            String sql = "DELETE from TOATHUOC "+
                         " WHERE MaToa= " + t.getMaToa();
            int kq = st.executeUpdate(sql);
            
            System.out.println("Bạn đã thực thi");
            JDBCUtil.closeConnection(connect);
            
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ObservableList<ToaThuoc> selectAll() {
        ObservableList<ToaThuoc> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            
            Statement st = con.createStatement();
            
            String sql = "SELECT * FROM TOATHUOC";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int MaToa = rs.getInt("MaToa");
                int MaPK = rs.getInt("MaPK");
                Date NgayKeToa = rs.getDate("NgayKeToa");
                BigDecimal TongTien = rs.getBigDecimal("TongTien");
                
                ToaThuoc toaThuoc = new ToaThuoc(MaToa, MaPK, NgayKeToa, TongTien);
                kq.add(toaThuoc);
            }
            
            JDBCUtil.closeConnection(con);    
        } catch (Exception e) {
            // TODO: handle exception
        }
        // TODO Auto-generated method stub
        return kq;
    }

    @Override
    public ToaThuoc seclectById(ToaThuoc t) {
        ToaThuoc kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            
            Statement st = con.createStatement();
            
            String sql = "SELECT * FROM TOATHUOC where MaToa=" + t.getMaToa();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int MaToa = rs.getInt("MaToa");
                int MaPK = rs.getInt("MaPK");
                Date NgayKeToa = rs.getDate("NgayKeToa");
                BigDecimal TongTien = rs.getBigDecimal("TongTien");
                
                kq = new ToaThuoc(MaToa, MaPK, NgayKeToa, TongTien);
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        // TODO Auto-generated method stub
        return kq;
    }
    
    @Override
    public ArrayList<ToaThuoc> selectByCondition(String condition) {
        ArrayList<ToaThuoc> kq = new ArrayList<>();
        
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            
            String sql = "SELECT * FROM TOATHUOC WHERE " + condition;
            System.out.println(sql); 
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int MaToa = rs.getInt("MaToa");
                int MaPK = rs.getInt("MaPK");
                Date NgayKeToa = rs.getDate("NgayKeToa");
                BigDecimal TongTien = rs.getBigDecimal("TongTien");
                
                ToaThuoc toaThuoc = new ToaThuoc(MaToa, MaPK, NgayKeToa, TongTien);
                kq.add(toaThuoc);
            }
            
            JDBCUtil.closeConnection(con);    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return kq;
    }
    
    public ToaThuoc selectByCondition2(String condition) {
    	ToaThuoc kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            
            String sql = "SELECT * FROM TOATHUOC WHERE " + condition;
            System.out.println(sql); 
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int MaToa = rs.getInt("MaToa");
                int MaPK = rs.getInt("MaPK");
                Date NgayKeToa = rs.getDate("NgayKeToa");
                BigDecimal TongTien = rs.getBigDecimal("TongTien");
                
                kq = new ToaThuoc(MaToa, MaPK, NgayKeToa, TongTien);
            }
            
            JDBCUtil.closeConnection(con);    
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return kq;
    }
}
