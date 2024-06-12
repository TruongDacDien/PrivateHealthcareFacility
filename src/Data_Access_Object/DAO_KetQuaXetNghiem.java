package Data_Access_Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.KetQuaXetNghiem;

public class DAO_KetQuaXetNghiem implements Interface<KetQuaXetNghiem> {
	public static DAO_KetQuaXetNghiem getInstance() {
		return new DAO_KetQuaXetNghiem();
	}

	@Override
	public int Add(KetQuaXetNghiem t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			Statement st = connect.createStatement();
			String sql = "INSERT INTO KETQUAXETNGHIEM(MaXN, MoTa, KetLuan, LoiKhuyen, NgayXN) VALUES (" + t.getMaXN()
					+ ", '" + t.getMoTa() + "', '" + t.getKetLuan() + "', '" + t.getLoiKhuyen() + "', '" + t.getNgayXN()
					+ "')";
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
	public int Update(KetQuaXetNghiem t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			Statement st = connect.createStatement();
			String sql = "UPDATE KETQUAXETNGHIEM SET MaXN= " + t.getMaXN() + ", MoTa= '" + t.getMoTa() + "', KetLuan= '"
					+ t.getKetLuan() + "', LoiKhuyen= '" + t.getLoiKhuyen() + "', NgayXN= '" + t.getNgayXN()
					+ "' WHERE MaKQXN= " + t.getMaKQXN();
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
	public int Delete(KetQuaXetNghiem t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			Statement st = connect.createStatement();
			String sql = "DELETE FROM KETQUAXETNGHIEM WHERE MaKQXN= " + t.getMaKQXN();
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
	public ObservableList<KetQuaXetNghiem> selectAll() {
		ObservableList<KetQuaXetNghiem> kq = FXCollections.observableArrayList();
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM KETQUAXETNGHIEM";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int MaKQXN = rs.getInt("MaKQXN");
				int MaXN = rs.getInt("MaXN"); // Retrieve MaXN
				String MoTa = rs.getString("MoTa");
				String KetLuan = rs.getString("KetLuan");
				String LoiKhuyen = rs.getString("LoiKhuyen");
				Date NgayXN = rs.getDate("NgayXN");
				KetQuaXetNghiem ketquaXetNghiem = new KetQuaXetNghiem(MaKQXN, MaXN, MoTa, KetLuan, LoiKhuyen, NgayXN);
				kq.add(ketquaXetNghiem);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public KetQuaXetNghiem seclectById(KetQuaXetNghiem t) {
		KetQuaXetNghiem kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM KETQUAXETNGHIEM WHERE MaKQXN=" + t.getMaKQXN();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int MaKQXN = rs.getInt("MaKQXN");
				int MaXN = rs.getInt("MaXN"); // Retrieve MaXN
				String MoTa = rs.getString("MoTa");
				String KetLuan = rs.getString("KetLuan");
				String LoiKhuyen = rs.getString("LoiKhuyen");
				Date NgayXN = rs.getDate("NgayXN");
				kq = new KetQuaXetNghiem(MaKQXN, MaXN, MoTa, KetLuan, LoiKhuyen, NgayXN);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public ArrayList<KetQuaXetNghiem> selectByCondition(String condition) {
		ArrayList<KetQuaXetNghiem> kq = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM KETQUAXETNGHIEM WHERE " + condition;
			System.out.println(sql);

			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int MaKQXN = rs.getInt("MaKQXN");
				int MaXN = rs.getInt("MaXN"); // Retrieve MaXN
				String MoTa = rs.getString("MoTa");
				String KetLuan = rs.getString("KetLuan");
				String LoiKhuyen = rs.getString("LoiKhuyen");
				Date NgayXN = rs.getDate("NgayXN");
				KetQuaXetNghiem ketquaXetNghiem = new KetQuaXetNghiem(MaKQXN, MaXN, MoTa, KetLuan, LoiKhuyen, NgayXN);
				kq.add(ketquaXetNghiem);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	public KetQuaXetNghiem selectByCondition2(String condition) {
		KetQuaXetNghiem kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM KETQUAXETNGHIEM WHERE " + condition;
			System.out.println(sql);

			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int MaKQXN = rs.getInt("MaKQXN");
				int MaXN = rs.getInt("MaXN"); // Retrieve MaXN
				String MoTa = rs.getString("MoTa");
				String KetLuan = rs.getString("KetLuan");
				String LoiKhuyen = rs.getString("LoiKhuyen");
				Date NgayXN = rs.getDate("NgayXN");
				kq = new KetQuaXetNghiem(MaKQXN, MaXN, MoTa, KetLuan, LoiKhuyen, NgayXN);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}
}
