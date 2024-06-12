package Insert;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import Data_Access_Object.DAO_PhieuKham;
import models.PhieuKham;

public class PhieuKham_Insert {
	public static void main(String[] args) {
		
		double N_1 = 47.35;
		double N_2 = 14.00;
		BigDecimal n1 = new BigDecimal(N_1);
		BigDecimal n2 = new BigDecimal(N_2);
		LocalDate slDate = LocalDate.of(2012,04,21);
		Date b = Date.valueOf(slDate);
		
		PhieuKham PhieuKham1 = new PhieuKham(0, 0, 0, 0, b, null, null, null, null, n2);
		
		DAO_PhieuKham.getInstance().Add(PhieuKham1);
	}
}
