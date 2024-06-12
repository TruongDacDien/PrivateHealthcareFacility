package models;

import java.sql.Date;

public class BaoCaoSuDungThuoc {
	private int MaBCThuoc;
	private Date NgayLapThuoc;
	private int MaThuoc;
	private int SoLuongSuDung;
	private int SoLuongConLai;
	public BaoCaoSuDungThuoc() {
		super();
	}
	public BaoCaoSuDungThuoc(int maBCThuoc, Date ngayLapThuoc, int maThuoc, int soLuongSuDung, int soLuongConLai) {
		super();
		MaBCThuoc = maBCThuoc;
		NgayLapThuoc = ngayLapThuoc;
		MaThuoc = maThuoc;
		SoLuongSuDung = soLuongSuDung;
		SoLuongConLai = soLuongConLai;
	}
	public int getMaBCThuoc() {
		return MaBCThuoc;
	}
	public void setMaBCThuoc(int maBCThuoc) {
		MaBCThuoc = maBCThuoc;
	}
	public Date getNgayLapThuoc() {
		return NgayLapThuoc;
	}
	public void setNgayLapThuoc(Date ngayLapThuoc) {
		NgayLapThuoc = ngayLapThuoc;
	}
	public int getMaThuoc() {
		return MaThuoc;
	}
	public void setMaThuoc(int maThuoc) {
		MaThuoc = maThuoc;
	}
	public int getSoLuongSuDung() {
		return SoLuongSuDung;
	}
	public void setSoLuongSuDung(int soLuongSuDung) {
		SoLuongSuDung = soLuongSuDung;
	}
	public int getSoLuongConLai() {
		return SoLuongConLai;
	}
	public void setSoLuongConLai(int soLuongConLai) {
		SoLuongConLai = soLuongConLai;
	}
	@Override
	public String toString() {
		return "BaoCaoSuDungThuoc [MaBCThuoc=" + MaBCThuoc + ", NgayLapThuoc=" + NgayLapThuoc + ", MaThuoc=" + MaThuoc
				+ ", SoLuongSuDung=" + SoLuongSuDung + ", SoLuongConLai=" + SoLuongConLai + "]";
	}
}
