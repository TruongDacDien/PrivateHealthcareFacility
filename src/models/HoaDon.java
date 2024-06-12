package models;

import java.math.BigDecimal;

public class HoaDon {
	int Number;
	String Name;
	private BigDecimal Price;
	
	
	public int getNumber() {
		return Number;
	}
	public void setNumber(int number) {
		Number = number;
	}
	public String getName() {
		return Name;
	}
	public BigDecimal getPrice() {
		return Price;
	}
	public void setName(String name) {
		Name = name;
	}
	public void setPrice(BigDecimal price) {
		Price = price;
	}
	public HoaDon(int number,String name,BigDecimal price ) {
		Name = name;
		Price = price;
		Number = number;
	}
	
}
