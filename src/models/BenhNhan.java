package models;

import java.sql.Date;

public class BenhNhan {
	private int MaBN;
	private String HoTen;
	private Date NgaySinh;
	private String GioiTinh;
	private String DiaChi;
	private String SDT;
	private String Image_BN;
	private boolean Status;
	private String Status_Str;

	public BenhNhan() {
		super();
	}

	public BenhNhan(int maBN, String hoTen, Date ngaySinh, String gioiTinh, String diaChi, String sDT, String image_BN, boolean status) {
		super();
		MaBN = maBN;
		HoTen = hoTen;
		NgaySinh = ngaySinh;
		GioiTinh = gioiTinh;
		DiaChi = diaChi;
		SDT = sDT;
		Image_BN = image_BN;
		Status = status;
		if(status)
			Status_Str= "Active";
		else {
			Status_Str= "Inactive";
		}
	}

	public int getMaBN() {
		return MaBN;
	}

	public void setMaBN(int maBN) {
		MaBN = maBN;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public Date getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getImage_BN() {
		return Image_BN;
	}

	public void setImage_BN(String image_BN) {
		Image_BN = image_BN;
	}

	public boolean getStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}
	
	public String getStatus_Str() {
		return Status_Str;
	}
	
	public void setStatus_Str(String status_Str) {
		Status_Str = status_Str;
		if(Status_Str.equals("Active")) {
			Status = true;
		}
		else Status = false;
	}

	@Override
	public String toString() {
		return this.getMaBN() + " - " + this.getHoTen();
	}
}
