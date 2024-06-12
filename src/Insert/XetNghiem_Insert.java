package Insert;


import java.math.BigDecimal;

import Data_Access_Object.DAO_XetNghiem;
import models.XetNghiem;

public class XetNghiem_Insert {
	public static void main(String[] args) {
		
		double N_1 =0.0;
		BigDecimal n1 = new BigDecimal(N_1);
		XetNghiem XetNghiem1 = new XetNghiem(0, 0, 0, 0, null, null,null, n1,true);
		
		DAO_XetNghiem.getInstance().Add(XetNghiem1);
		
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
