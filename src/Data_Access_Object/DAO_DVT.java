package Data_Access_Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.DVT;

public class DAO_DVT implements Interface<DVT> {
    
    public static DAO_DVT getInstance() {
        return new DAO_DVT(); 
    }
    
    @Override
    public int Add(DVT t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "INSERT INTO DVT(Ten_DVT, Status) VALUES ('" + t.getTen_DVT() + "', " + t.getStatus() + ")";
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int Update(DVT t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE DVT SET Ten_DVT= '" + t.getTen_DVT() + "', Status= " + t.getStatus() + " WHERE Ma_DVT= " + t.getMa_DVT();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int UpdateStatus(DVT t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE DVT SET Status= " + t.getStatus() + " WHERE Ma_DVT= " + t.getMa_DVT();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public int Delete(DVT t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "DELETE FROM DVT WHERE Ma_DVT= " + t.getMa_DVT();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ObservableList<DVT> selectAll() {
        ObservableList<DVT> resultList = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM DVT";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int Ma_DVT = rs.getInt("Ma_DVT");
                String Ten_DVT = rs.getString("Ten_DVT");
                boolean Status = rs.getBoolean("Status");
                DVT dvt = new DVT(Ma_DVT, Ten_DVT, Status);
                resultList.add(dvt);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public DVT seclectById(DVT t) {
        DVT result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM DVT WHERE Ma_DVT=" + t.getMa_DVT();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int Ma_DVT = rs.getInt("Ma_DVT");
                String Ten_DVT = rs.getString("Ten_DVT");
                boolean Status = rs.getBoolean("Status");
                result = new DVT(Ma_DVT, Ten_DVT, Status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<DVT> selectByCondition(String condition) {
        ArrayList<DVT> resultList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM DVT WHERE " + condition;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int Ma_DVT = rs.getInt("Ma_DVT");
                String Ten_DVT = rs.getString("Ten_DVT");
                boolean Status = rs.getBoolean("Status");
                DVT dvt = new DVT(Ma_DVT, Ten_DVT, Status);
                resultList.add(dvt);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
    
    public ObservableList<DVT> selectByCondition2(String condition) {
    	ObservableList<DVT> resultList = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM DVT WHERE " + condition;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int Ma_DVT = rs.getInt("Ma_DVT");
                String Ten_DVT = rs.getString("Ten_DVT");
                boolean Status = rs.getBoolean("Status");
                DVT dvt = new DVT(Ma_DVT, Ten_DVT, Status);
                resultList.add(dvt);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
