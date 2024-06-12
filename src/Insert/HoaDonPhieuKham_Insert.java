package Insert;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import Data_Access_Object.DAO_HoaDonPhieuKham;
import models.HoaDonPhieuKham;

public class HoaDonPhieuKham_Insert {
	public static void main(String[] args) {
		
		LocalDate slDate = LocalDate.of(2012,04,21);
		Date b = Date.valueOf(slDate);
		
		double N_1 = 14.00;
		BigDecimal n1 = new BigDecimal(N_1);
		
		//HoaDonPhieuKham HoaDonPhieuKham1 = new HoaDonPhieuKham(0, 0, b, n1);
		
		//DAO_HoaDonPhieuKham.getInstance().Add(HoaDonPhieuKham1);
		
	}
}
