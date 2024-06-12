package Insert;

import Data_Access_Object.DAO_Anh;
import models.Anh;

public class Anh_Insert {
	public static void main(String[] args) {
		
		
		Anh Anh1 = new Anh(0, 0,null,true);
		
		DAO_Anh.getInstance().Add(Anh1);
		
		
	}
}
