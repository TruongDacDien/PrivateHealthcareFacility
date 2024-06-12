package models;

import java.math.BigDecimal;
import java.sql.Date;

public class PhieuKham {
	private int MaPK;
	private int Id_User;
	private int MaBN;
	private int MaBS;
	private Date NgayLap;
	private String TrangThai;
	private String LoiKhuyen;
	private String TrieuChung;
	private String ChuanDoan;
	private BigDecimal TienKham;
	public PhieuKham() {
		super();
	}
	public PhieuKham(int maPK, int id_User, int maBN, int maBS, Date ngayLap, String trangThai, String loiKhuyen,
			String trieuChung, String chuanDoan, BigDecimal tienKham) {
		super();
		MaPK = maPK;
		Id_User = id_User;
		MaBN = maBN;
		MaBS = maBS;
		NgayLap = ngayLap;
		TrangThai = trangThai;
		LoiKhuyen = loiKhuyen;
		TrieuChung = trieuChung;
		ChuanDoan = chuanDoan;
		TienKham = tienKham;
	}
	public int getMaPK() {
		return MaPK;
	}
	public void setMaPK(int maPK) {
		MaPK = maPK;
	}
	public int getId_User() {
		return Id_User;
	}
	public void setId_User(int id_User) {
		Id_User = id_User;
	}
	public int getMaBN() {
		return MaBN;
	}
	public void setMaBN(int maBN) {
		MaBN = maBN;
	}
	public int getMaBS() {
		return MaBS;
	}
	public void setMaBS(int maBS) {
		MaBS = maBS;
	}
	public Date getNgayLap() {
		return NgayLap;
	}
	public void setNgayLap(Date ngayLap) {
		NgayLap = ngayLap;
	}
	public String getTrangThai() {
		return TrangThai;
	}
	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}
	public String getLoiKhuyen() {
		return LoiKhuyen;
	}
	public void setLoiKhuyen(String loiKhuyen) {
		LoiKhuyen = loiKhuyen;
	}
	public String getTrieuChung() {
		return TrieuChung;
	}
	public void setTrieuChung(String trieuChung) {
		TrieuChung = trieuChung;
	}
	public String getChuanDoan() {
		return ChuanDoan;
	}
	public void setChuanDoan(String chuanDoan) {
		ChuanDoan = chuanDoan;
	}
	public BigDecimal getTienKham() {
		return TienKham;
	}
	public void setTienKham(BigDecimal tienKham) {
		TienKham = tienKham;
	}
	@Override
	public String toString() {
		return "PhieuKham [MaPK=" + MaPK + ", Id_User=" + Id_User + ", MaBN=" + MaBN + ", MaBS=" + MaBS + ", NgayLap="
				+ NgayLap + ", TrangThai=" + TrangThai + ", LoiKhuyen=" + LoiKhuyen + ", TrieuChung=" + TrieuChung
				+ ", ChuanDoan=" + ChuanDoan + ", TienKham=" + TienKham + "]";
	}
	
}
