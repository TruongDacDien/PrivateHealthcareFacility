package models;

import java.math.BigDecimal;

public class LoaiXetNghiem {
	private int MaLoaiXN;
	private String TenLoaiXN;
	private BigDecimal ChiPhiXN;
	private String MoTaXN;
	private boolean Status;

	public LoaiXetNghiem() {
		super();
	}

	public LoaiXetNghiem(int maLoaiXN, String tenLoaiXN, BigDecimal chiPhiXN, String moTaXN, boolean status) {
		super();
		MaLoaiXN = maLoaiXN;
		TenLoaiXN = tenLoaiXN;
		ChiPhiXN = chiPhiXN;
		MoTaXN = moTaXN;
		Status = status;
	}

	public int getMaLoaiXN() {
		return MaLoaiXN;
	}

	public void setMaLoaiXN(int maLoaiXN) {
		MaLoaiXN = maLoaiXN;
	}

	public String getTenLoaiXN() {
		return TenLoaiXN;
	}

	public void setTenLoaiXN(String tenLoaiXN) {
		TenLoaiXN = tenLoaiXN;
	}

	public BigDecimal getChiPhiXN() {
		return ChiPhiXN;
	}

	public void setChiPhiXN(BigDecimal chiPhiXN) {
		ChiPhiXN = chiPhiXN;
	}

	public String getMoTaXN() {
		return MoTaXN;
	}

	public void setMoTaXN(String moTaXN) {
		MoTaXN = moTaXN;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	
	@Override
	public String toString() {
		return this.getMaLoaiXN() + " - " + this.getTenLoaiXN() ;
	}
}
