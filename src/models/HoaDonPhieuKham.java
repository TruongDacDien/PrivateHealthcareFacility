package models;

import java.math.BigDecimal;
import java.sql.Date;

public class HoaDonPhieuKham {
	private int MaHD;
	private int MaPK;
	private Date NgayBan;
	private BigDecimal TongTien;
	private Boolean IsPaid;
	public HoaDonPhieuKham() {
		super();
		MaHD = -1;
	}
	
	public HoaDonPhieuKham(int maHD, int maPK, Date ngayBan, BigDecimal tongTien,Boolean isPaid) {
		super();
		MaHD = maHD;
		MaPK = maPK;
		NgayBan = ngayBan;
		TongTien = tongTien;
		IsPaid = isPaid;
	}
	public Boolean getIsPaid() {
		return IsPaid;
	}
	
	public void setIsPaid(Boolean isPaid) {
		IsPaid = isPaid;
	}
	public int getMaHD() {
		return MaHD;
	}
	public void setMaHD(int maHD) {
		MaHD = maHD;
	}
	public int getMaPK() {
		return MaPK;
	}
	public void setMaPK(int maPK) {
		MaPK = maPK;
	}
	public Date getNgayBan() {
		return NgayBan;
	}
	public void setNgayBan(Date ngayBan) {
		NgayBan = ngayBan;
	}
	public BigDecimal getTongTien() {
		return TongTien;
	}
	public void setTongTien(BigDecimal tongTien) {
		TongTien = tongTien;
	}
	@Override
	public String toString() {
		return "HoaDonPhieuKham [MaHD=" + MaHD + ", MaPK=" + MaPK + ", NgayBan=" + NgayBan + ", TongTien=" + TongTien
				+ "]";
	}
}
