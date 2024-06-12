package Insert;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import Data_Access_Object.DAO_LoaiXetNghiem;
import models.LoaiXetNghiem;

public class LoaiXetNghiem_Insert {
	public static void main(String[] args) {
		
		
		double N_1 = 14.00;
		BigDecimal n1 = new BigDecimal(N_1);
		
		LoaiXetNghiem LoaiXetNghiem1 = new LoaiXetNghiem(0, null, n1, null,true);
		
		DAO_LoaiXetNghiem.getInstance().Add(LoaiXetNghiem1);
		
		
	}
}
