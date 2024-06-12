package models;

import java.math.BigDecimal;
import java.sql.Date;

public class ToaThuoc {
	private int MaToa;
	private int MaPK;
	private Date NgayKeToa;
	private BigDecimal TongTien;

	public ToaThuoc() {
		super();
	}

	public ToaThuoc(int maToa, int maPK, Date ngayKeToa, BigDecimal tongTien) {
		super();
		MaToa = maToa;
		MaPK = maPK;
		NgayKeToa = ngayKeToa;
		TongTien = tongTien;
	}

	public int getMaToa() {
		return MaToa;
	}

	public void setMaToa(int maToa) {
		MaToa = maToa;
	}

	public int getMaPK() {
		return MaPK;
	}

	public void setMaPK(int maPK) {
		MaPK = maPK;
	}

	public Date getNgayKeToa() {
		return NgayKeToa;
	}

	public void setNgayKeToa(Date ngayKeToa) {
		NgayKeToa = ngayKeToa;
	}

	public BigDecimal getTongTien() {
		return TongTien;
	}

	public void setTongTien(BigDecimal tongTien) {
		TongTien = tongTien;
	}

	@Override
	public String toString() {
		return "ToaThuoc [MaToa=" + MaToa + ", MaPK=" + MaPK + ", NgayKeToa=" + NgayKeToa + ", TongTien=" + TongTien + "]";
	}
}
