package Insert;

import java.sql.Date;
import java.time.LocalDate;

import Data_Access_Object.DAO_KetQuaXetNghiem;
import models.KetQuaXetNghiem;

public class KetQuaXetNghiem_Insert {
	public static void main(String[] args) {
		
		LocalDate slDate = LocalDate.of(2012,04,21);
		Date b = Date.valueOf(slDate);

		KetQuaXetNghiem KetQuaXetNghiem1 = new KetQuaXetNghiem(0,0,null, null, null,b);
		
		DAO_KetQuaXetNghiem.getInstance().Add(KetQuaXetNghiem1);
		
		
	}
}
