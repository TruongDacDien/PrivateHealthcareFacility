package Insert;

import java.sql.Date;
import java.time.LocalDate;

import Data_Access_Object.DAO_BaoCaoSuDungThuoc;
import models.BaoCaoSuDungThuoc;

public class BaoCaoSuDungThuoc_Insert {
	public static void main(String[] args) {
		
		LocalDate slDate = LocalDate.of(2012,04,21);
		Date b = Date.valueOf(slDate);
		
		BaoCaoSuDungThuoc BaoCaoSuDungThuoc1 = new BaoCaoSuDungThuoc(0, b, 0, 0, 0);
		
		DAO_BaoCaoSuDungThuoc.getInstance().Add(BaoCaoSuDungThuoc1);
		
	}
}
