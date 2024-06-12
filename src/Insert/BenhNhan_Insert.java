package Insert;

import java.sql.Date;
import java.time.LocalDate;

import Data_Access_Object.DAO_BenhNhan;
import models.BenhNhan;

public class BenhNhan_Insert {
	public static void main(String[] args) {
		
		LocalDate slDate = LocalDate.of(2012,04,21);
		Date b = Date.valueOf(slDate);
		
		BenhNhan BenhNhan1 = new BenhNhan(0, null, b, null, null, null, null,true);
		
		DAO_BenhNhan.getInstance().Add(BenhNhan1);
		
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
