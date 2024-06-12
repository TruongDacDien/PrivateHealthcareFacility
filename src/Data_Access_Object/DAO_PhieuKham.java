package Data_Access_Object;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.PhieuKham;

public class DAO_PhieuKham implements Interface<PhieuKham> {
	public static DAO_PhieuKham getInstance() {
		return new DAO_PhieuKham();
	}

	@Override
	public int Add(PhieuKham t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			Statement st = connect.createStatement();
			String sql = "INSERT INTO PHIEUKHAM(Id_User, MaBN, MaBS, NgayLap, TrangThai, LoiKhuyen, TrieuChung, ChuanDoan, TienKham) VALUES ("
					+ t.getId_User() + ", " + t.getMaBN() + ", " + t.getMaBS() + ", '" + t.getNgayLap() + "', '"
					+ t.getTrangThai() + "', '" + t.getLoiKhuyen() + "', '" + t.getTrieuChung() + "', '"
					+ t.getChuanDoan() + "', " + t.getTienKham() + ")";
			int kq = st.executeUpdate(sql);
			System.out.println("Bạn đã thực thi: " + sql);
			JDBCUtil.closeConnection(connect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int AddAndGetID(PhieuKham phieuKham) {
		int generatedId = -1;
		String sql = "INSERT INTO PHIEUKHAM(Id_User, MaBN, MaBS, NgayLap, TrangThai, LoiKhuyen, TrieuChung, ChuanDoan, TienKham) VALUES (?,?,?,?,?,?,?,?,?)";
		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setInt(1, phieuKham.getId_User());
			preparedStatement.setInt(2, phieuKham.getMaBN());
			preparedStatement.setInt(3, phieuKham.getMaBS());
			preparedStatement.setDate(4, phieuKham.getNgayLap());
			preparedStatement.setString(5, phieuKham.getTrangThai());
			preparedStatement.setString(6, phieuKham.getLoiKhuyen());
			preparedStatement.setString(7, phieuKham.getTrieuChung());
			preparedStatement.setString(8, phieuKham.getChuanDoan());
			preparedStatement.setBigDecimal(9, phieuKham.getTienKham());

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
	public int Update(PhieuKham t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			Statement st = connect.createStatement();
			String sql = "UPDATE PHIEUKHAM SET Id_User= " + t.getId_User() + ", MaBN= " + t.getMaBN() + ", MaBS= "
					+ t.getMaBS() + ", NgayLap= '" + t.getNgayLap() + "', TrangThai= '" + t.getTrangThai()
					+ "', LoiKhuyen= '" + t.getLoiKhuyen() + "', TrieuChung= '" + t.getTrieuChung() + "', ChuanDoan= '"
					+ t.getChuanDoan() + "', TienKham= " + t.getTienKham() + " WHERE MaPK= " + t.getMaPK();
			int kq = st.executeUpdate(sql);
			System.out.println("Bạn đã thực thi: " + sql);
			JDBCUtil.closeConnection(connect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int Delete(PhieuKham t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			Statement st = connect.createStatement();
			String sql = "DELETE FROM PHIEUKHAM WHERE MaPK= " + t.getMaPK();
			int kq = st.executeUpdate(sql);
			System.out.println("Bạn đã thực thi: " + sql);
			JDBCUtil.closeConnection(connect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ObservableList<PhieuKham> selectAll() {
		ObservableList<PhieuKham> kq = FXCollections.observableArrayList();
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM PHIEUKHAM";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int MaPK = rs.getInt("MaPK");
				int Id_User = rs.getInt("Id_User");
				int MaBN = rs.getInt("MaBN");
				int MaBS = rs.getInt("MaBS");
				Date NgayLap = rs.getDate("NgayLap");
				String TrangThai = rs.getString("TrangThai");
				String LoiKhuyen = rs.getString("LoiKhuyen");
				String TrieuChung = rs.getString("TrieuChung");
				String ChuanDoan = rs.getString("ChuanDoan");
				BigDecimal TienKham = rs.getBigDecimal("TienKham");
				PhieuKham phieuKham = new PhieuKham(MaPK, Id_User, MaBN, MaBS, NgayLap, TrangThai, LoiKhuyen,
						TrieuChung, ChuanDoan, TienKham);
				kq.add(phieuKham);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public PhieuKham seclectById(PhieuKham t) {
		PhieuKham kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM PHIEUKHAM WHERE MaPK=" + t.getMaPK();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int MaPK = rs.getInt("MaPK");
				int Id_User = rs.getInt("Id_User");
				int MaBN = rs.getInt("MaBN");
				int MaBS = rs.getInt("MaBS");
				Date NgayLap = rs.getDate("NgayLap");
				String TrangThai = rs.getString("TrangThai");
				String LoiKhuyen = rs.getString("LoiKhuyen");
				String TrieuChung = rs.getString("TrieuChung");
				String ChuanDoan = rs.getString("ChuanDoan");
				BigDecimal TienKham = rs.getBigDecimal("TienKham");
				kq = new PhieuKham(MaPK, Id_User, MaBN, MaBS, NgayLap, TrangThai, LoiKhuyen, TrieuChung, ChuanDoan,
						TienKham);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public ArrayList<PhieuKham> selectByCondition(String condition) {
		ArrayList<PhieuKham> kq = new ArrayList<>();

		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM PHIEUKHAM WHERE " + condition;
			System.out.println(sql);

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int MaPK = rs.getInt("MaPK");
				int Id_User = rs.getInt("Id_User");
				int MaBN = rs.getInt("MaBN");
				int MaBS = rs.getInt("MaBS");
				Date NgayLap = rs.getDate("NgayLap");
				String TrangThai = rs.getString("TrangThai");
				String LoiKhuyen = rs.getString("LoiKhuyen");
				String TrieuChung = rs.getString("TrieuChung");
				String ChuanDoan = rs.getString("ChuanDoan");
				BigDecimal TienKham = rs.getBigDecimal("TienKham");

				PhieuKham phieuKham = new PhieuKham(MaPK, Id_User, MaBN, MaBS, NgayLap, TrangThai, LoiKhuyen,
						TrieuChung, ChuanDoan, TienKham);
				kq.add(phieuKham);
			}

			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return kq;
	}
	public ObservableList<PhieuKham> selectByCondition2(String condition) {
		ObservableList<PhieuKham> kq = FXCollections.observableArrayList();
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM PHIEUKHAM WHERE " + condition;
			System.out.println(sql);

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int MaPK = rs.getInt("MaPK");
				int Id_User = rs.getInt("Id_User");
				int MaBN = rs.getInt("MaBN");
				int MaBS = rs.getInt("MaBS");
				Date NgayLap = rs.getDate("NgayLap");
				String TrangThai = rs.getString("TrangThai");
				String LoiKhuyen = rs.getString("LoiKhuyen");
				String TrieuChung = rs.getString("TrieuChung");
				String ChuanDoan = rs.getString("ChuanDoan");
				BigDecimal TienKham = rs.getBigDecimal("TienKham");

				PhieuKham phieuKham = new PhieuKham(MaPK, Id_User, MaBN, MaBS, NgayLap, TrangThai, LoiKhuyen,
						TrieuChung, ChuanDoan, TienKham);
				kq.add(phieuKham);
			}

			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}
}
