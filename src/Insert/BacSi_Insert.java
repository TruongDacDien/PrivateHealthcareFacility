package Insert;


import Data_Access_Object.DAO_BacSi;
import models.BacSi;

public class BacSi_Insert {
	public static void main(String[] args) {
		
		
		BacSi bacsi = new BacSi(0, null, null,null, null, null, null,0, null,true);
		
		DAO_BacSi.getInstance().Add(bacsi);
		
		/* Phần select (thay Agent thành các tên bảng phù hợp, kể cả số lượng tp trong '()' )
		Agent find = new Agent(22520603, "Kha", "0868853033", "HoChiMinh", "LinhTrung", 5000000);
		Agent rs = DAO_Agent.getInstance().seclectById(find);
		System.out.println(rs);
		*/
		
		/*
		ArrayList<Agent> list = DAO_Agent.getInstance().selectAll();
		for (Agent agent : list) {
			System.out.println(agent.toString());
		}
		*/
		
		/*
		ArrayList<Agent> list = DAO_Agent.getInstance().selectByCondition("Agent_Id != 22520601");
		for (Agent agent : list) {
			System.out.println(agent.toString());
		}
		*/
	}
}
