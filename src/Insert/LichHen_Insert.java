package Insert;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import Data_Access_Object.DAO_LichHen;
import models.LichHen;

public class LichHen_Insert {
	public static void main(String[] args) {
		
		LocalDateTime slDate = LocalDateTime.of(2012, 4, 21, 12, 30);
		Timestamp  b = Timestamp.valueOf(slDate);
		
		LichHen LichHen1 = new LichHen(0, 0, 0, b, null,true);
		
		DAO_LichHen.getInstance().Add(LichHen1);
		
	}
}
