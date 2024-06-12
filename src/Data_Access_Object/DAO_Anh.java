package Data_Access_Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Anh;

public class DAO_Anh implements Interface<Anh> {
	public static DAO_Anh getInstance() {
		return new DAO_Anh();
	}

	@Override
	public int Add(Anh t) {
		int result = 0;
		try {
			Connection connect = JDBCUtil.getConnection();
			Statement st = connect.createStatement();
			String sql = "INSERT INTO ANH(MaKQXN, DuongDan, Status) VALUES (" + t.getMaKQXN() + ", '" + t.getDuongDan()
					+ "', " + t.getStatus() + ")";
			result = st.executeUpdate(sql);
			System.out.println("Bạn đã thực thi: " + sql);
			JDBCUtil.closeConnection(connect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int Update(Anh t) {
		int result = 0;
		try {
			Connection connect = JDBCUtil.getConnection();
			Statement st = connect.createStatement();
			String sql = "UPDATE ANH SET MaKQXN= " + t.getMaKQXN() + ", DuongDan= '" + t.getDuongDan() + "', Status= "
					+ t.getStatus() + " WHERE MaAnh= " + t.getMaAnh();
			result = st.executeUpdate(sql);
			System.out.println("Bạn đã thực thi: " + sql);
			JDBCUtil.closeConnection(connect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int UpdateStatus(Anh t) {
		int result = 0;
		try {
			Connection connect = JDBCUtil.getConnection();
			Statement st = connect.createStatement();
			String sql = "UPDATE ANH SET Status= "
					+ t.getStatus() + " WHERE MaAnh= " + t.getMaAnh();
			result = st.executeUpdate(sql);
			System.out.println("Bạn đã thực thi: " + sql);
			JDBCUtil.closeConnection(connect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int Delete(Anh t) {
		int result = 0;
		try {
			Connection connect = JDBCUtil.getConnection();
			Statement st = connect.createStatement();
			String sql = "DELETE FROM ANH WHERE MaAnh= " + t.getMaAnh();
			result = st.executeUpdate(sql);
			System.out.println("Bạn đã thực thi: " + sql);
			JDBCUtil.closeConnection(connect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ObservableList<Anh> selectAll() {
		ObservableList<Anh> resultList = FXCollections.observableArrayList();
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM ANH";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int MaAnh = rs.getInt("MaAnh");
				int MaKQXN = rs.getInt("MaKQXN"); // Retrieve MaKQXN
				String DuongDan = rs.getString("DuongDan");
				boolean status = rs.getBoolean("Status");
				Anh anh = new Anh(MaAnh, MaKQXN, DuongDan, status);
				resultList.add(anh);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	@Override
	public Anh seclectById(Anh t) {
		Anh result = null;
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM ANH WHERE MaAnh=" + t.getMaAnh();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				int MaAnh = rs.getInt("MaAnh");
				int MaKQXN = rs.getInt("MaKQXN"); // Retrieve MaKQXN
				String DuongDan = rs.getString("DuongDan");
				boolean status = rs.getBoolean("Status");
				result = new Anh(MaAnh, MaKQXN, DuongDan, status);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Anh> selectByCondition(String condition) {
		ArrayList<Anh> resultList = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM ANH WHERE " + condition;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int MaAnh = rs.getInt("MaAnh");
				int MaKQXN = rs.getInt("MaKQXN"); // Retrieve MaKQXN
				String DuongDan = rs.getString("DuongDan");
				boolean status = rs.getBoolean("Status");
				Anh anh = new Anh(MaAnh, MaKQXN, DuongDan, status);
				resultList.add(anh);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public ObservableList<Anh> selectByCondition2(String condition) {
		ObservableList<Anh> resultList = FXCollections.observableArrayList();
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM ANH WHERE " + condition;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int MaAnh = rs.getInt("MaAnh");
				int MaKQXN = rs.getInt("MaKQXN"); // Retrieve MaKQXN
				String DuongDan = rs.getString("DuongDan");
				boolean status = rs.getBoolean("Status");
				Anh anh = new Anh(MaAnh, MaKQXN, DuongDan, status);
				resultList.add(anh);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
}
