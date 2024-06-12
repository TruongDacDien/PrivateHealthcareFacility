package models;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Data_Access_Object.DAO_Thuoc;
import Database.JDBCUtil;

public class ChiTietToaThuoc {
	private int MaChiTietToa;
	private int MaToa;
	private int MaThuoc;
	private String TenThuoc;
	private int SoLuong;
	private String KhoangThoiGian;
	private String LieuLuong;
	private BigDecimal Tien;
	private boolean Status;
	private String DonVi;

	public ChiTietToaThuoc() {
		super();
	}

	public ChiTietToaThuoc(int maChiTietToa, int maToa, int maThuoc, String tenThuoc, int soLuong, String khoangThoiGian, String lieuLuong, BigDecimal tien, boolean status) {
		super();
		MaChiTietToa = maChiTietToa;
		MaToa = maToa;
		MaThuoc = maThuoc;
		TenThuoc = tenThuoc;
		SoLuong = soLuong;
		KhoangThoiGian = khoangThoiGian;
		LieuLuong = lieuLuong;
		Tien = tien;
		Status = status;
		
		
		Thuoc medicine = DAO_Thuoc.getInstance().seclectById(MaThuoc);
		if(medicine != null)
		DonVi = medicine.getDonVi();
		else {
			DonVi = null;
		}
		
	}

	public int getMaChiTietToa() {
		return MaChiTietToa;
	}

	public void setMaChiTietToa(int maChiTietToa) {
		MaChiTietToa = maChiTietToa;
	}

	public int getMaToa() {
		return MaToa;
	}

	public void setMaToa(int maToa) {
		MaToa = maToa;
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

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}

	public String getKhoangThoiGian() {
		return KhoangThoiGian;
	}

	public void setKhoangThoiGian(String khoangThoiGian) {
		KhoangThoiGian = khoangThoiGian;
	}

	public String getLieuLuong() {
		return LieuLuong;
	}

	public void setLieuLuong(String lieuLuong) {
		LieuLuong = lieuLuong;
	}

	public BigDecimal getTien() {
		return Tien;
	}

	public void setTien(BigDecimal tien) {
		Tien = tien;
	}

	public boolean getStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}
	
	public String getDonVi() {
		return DonVi;
	}

	@Override
	public String toString() {
		return "ChiTietToaThuoc [MaChiTietToa=" + MaChiTietToa + ", MaToa=" + MaToa + ", MaThuoc=" + MaThuoc
				+ ", TenThuoc=" + TenThuoc + ", SoLuong=" + SoLuong + ", KhoangThoiGian=" + KhoangThoiGian
				+ ", LieuLuong=" + LieuLuong + ", Tien=" + Tien + ", Status=" + Status + "]";
	}
}
