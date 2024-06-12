package models;

public class DVT {
	private int Ma_DVT;
	private String Ten_DVT;
	private boolean Status;

	public DVT() {
		super();
	}

	public DVT(int ma_DVT, String ten_DVT, boolean status) {
		super();
		Ma_DVT = ma_DVT;
		Ten_DVT = ten_DVT;
		Status = status;
	}

	public int getMa_DVT() {
		return Ma_DVT;
	}

	public void setMa_DVT(int ma_DVT) {
		Ma_DVT = ma_DVT;
	}

	public String getTen_DVT() {
		return Ten_DVT;
	}

	public void setTen_DVT(String ten_DVT) {
		Ten_DVT = ten_DVT;
	}

	public boolean getStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	@Override
	public String toString() {
		return this.getTen_DVT();
	}
}
