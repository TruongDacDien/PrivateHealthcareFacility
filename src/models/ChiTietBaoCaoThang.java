package models;

import java.math.BigDecimal;
import java.sql.Date;

public class ChiTietBaoCaoThang {
	Date NgayBC;
	int SoBenhNhan;
	BigDecimal DoanhThu;
	float TiLe;
	
	public ChiTietBaoCaoThang(Date ngayBc, int soBenhNhan,BigDecimal doanhThu,float tiLe) {
		NgayBC = ngayBc;
		SoBenhNhan = soBenhNhan;
		DoanhThu = doanhThu;
		TiLe = tiLe;
	}
	public BigDecimal getDoanhThu() {
		return DoanhThu;
	}
	public Date getNgayBC() {
		return NgayBC;
	}
	public float getTiLe() {
		return TiLe;
	}
	public void setDoanhThu(BigDecimal doanhThu) {
		DoanhThu = doanhThu;
	}
	public void setNgayBC(Date ngayBC) {
		NgayBC = ngayBC;
	}
	public void setSoBenhNhan(int soBenhNhan) {
		SoBenhNhan = soBenhNhan;
	}
	public void setTiLe(float tiLe) {
		TiLe = tiLe;
	}
	public int getSoBenhNhan() {
		return SoBenhNhan;
	}
}
