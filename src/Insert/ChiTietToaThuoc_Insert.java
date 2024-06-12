package Insert;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import Data_Access_Object.DAO_ChiTietToaThuoc;
import models.ChiTietToaThuoc;

public class ChiTietToaThuoc_Insert {
	public static void main(String[] args) {

		double N_1 = 14.00;
		BigDecimal n1 = new BigDecimal(N_1);

		ChiTietToaThuoc ChiTietToaThuoc1 = new ChiTietToaThuoc(0, 0, 0, null, 0, null, null, n1,true);

		DAO_ChiTietToaThuoc.getInstance().Add(ChiTietToaThuoc1);

	}
}
