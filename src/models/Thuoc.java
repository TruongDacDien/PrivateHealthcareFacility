package models;

import java.math.BigDecimal;
import java.sql.Date;

public class Thuoc {
	private int MaThuoc;
	private String TenThuoc;
	private String DonVi;
	private int SoLuong;
	private BigDecimal DonGia;
	private Date NgaySX;
	private Date HanSuDung;
	private boolean Status;
	

	public Thuoc() {
		super();
	}

	public Thuoc(int maThuoc, String tenThuoc, String donVi, int soLuong, BigDecimal donGia, Date ngaySX, Date hanSuDung, boolean status) {
		super();
		MaThuoc = maThuoc;
		TenThuoc = tenThuoc;
		DonVi = donVi;
		SoLuong = soLuong;
		DonGia = donGia;
		NgaySX = ngaySX;
		HanSuDung = hanSuDung;
		Status = status;
	}

	public int getMaThuoc() {
		return MaThuoc;
	}

	public void setMaThuoc(int maThuoc) {
		MaThuoc = maThuoc;
	}

	public String getTenThuoc() {
		return TenThuoc;
	}

	public void setTenThuoc(String tenThuoc) {
		TenThuoc = tenThuoc;
	}

	public String getDonVi() {
		return DonVi;
	}

	public void setDonVi(String donVi) {
		DonVi = donVi;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}

	public BigDecimal getDonGia() {
		return DonGia;
	}

	public void setDonGia(BigDecimal donGia) {
		DonGia = donGia;
	}

	public Date getNgaySX() {
		return NgaySX;
	}

	public void setNgaySX(Date ngaySX) {
		NgaySX = ngaySX;
	}

	public Date getHanSuDung() {
		return HanSuDung;
	}

	public void setHanSuDung(Date hanSuDung) {
		HanSuDung = hanSuDung;
	}

	public boolean getStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	@Override
	public String toString() {
		return this.getMaThuoc() + " - " + this.getTenThuoc();
	}
}
