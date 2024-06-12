package Insert;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import Data_Access_Object.DAO_Thuoc;
import models.Thuoc;

public class Thuoc_Insert {
	public static void main(String[] args) {
		
		LocalDate slDate = LocalDate.of(2012,04,21);
		Date b = Date.valueOf(slDate);
		
		double N_1 = 14.00;
		BigDecimal n1 = new BigDecimal(N_1); 
		
		//Thuoc Thuoc1 = new Thuoc(0, 0, null, null, 0,n1, b, b);
		
		//DAO_Thuoc.getInstance().Add(Thuoc1);
		
		
	}
}
