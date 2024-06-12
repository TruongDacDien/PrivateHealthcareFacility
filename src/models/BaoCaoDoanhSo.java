package models;

import java.math.BigDecimal;
import java.sql.Date;

public class BaoCaoDoanhSo {
	private int MaBCDoanhSo;
	private Date NgayLapDoanhSo;
	private BigDecimal DoanhSo;
	private int SoLuongPhieuKham;
	public BaoCaoDoanhSo() {
		super();
		SoLuongPhieuKham = -1;
	}
	public BaoCaoDoanhSo(int maBCDoanhSo, Date ngayLapDoanhSo, BigDecimal doanhSo, int maHD) {
		super();
		MaBCDoanhSo = maBCDoanhSo;
		NgayLapDoanhSo = ngayLapDoanhSo;
		DoanhSo = doanhSo;
		SoLuongPhieuKham = maHD;
	}
	public int getMaBCDoanhSo() {
		return MaBCDoanhSo;
	}
	public void setMaBCDoanhSo(int maBCDoanhSo) {
		MaBCDoanhSo = maBCDoanhSo;
	}
	public Date getNgayLapDoanhSo() {
		return NgayLapDoanhSo;
	}
	public void setNgayLapDoanhSo(Date ngayLapDoanhSo) {
		NgayLapDoanhSo = ngayLapDoanhSo;
	}
	public BigDecimal getDoanhSo() {
		return DoanhSo;
	}
	public void setDoanhSo(BigDecimal doanhSo) {
		DoanhSo = doanhSo;
	}
	public int getSoLuongPhieuKham() {
		return SoLuongPhieuKham;
	}
	public void setSoLuongPhieuKham(int soLuongPhieuKham) {
		SoLuongPhieuKham = soLuongPhieuKham;
	}
	@Override
	public String toString() {
		return "BaoCaoDoanhSo [MaBCDoanhSo=" + MaBCDoanhSo + ", NgayLapDoanhSo=" + NgayLapDoanhSo + ", DoanhSo="
				+ DoanhSo + ", MaHD=" + SoLuongPhieuKham + "]";
	}
	
}
