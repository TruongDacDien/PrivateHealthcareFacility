package models;

public class BacSi {
	private int MaBS;
	private String HoTen;
	private String ChuyenMon;
	private String DiaChi;
	private String SDT;
	private String GioiTinh;
	private String Email;
	private int NamKN;
	private String Image_BS;
	private boolean Status;

	public BacSi() {
		super();
	}

	public BacSi(int maBS, String hoTen, String chuyenMon, String diaChi, String sDT, String gioiTinh, String email,
			int namKN, String image_BS, boolean status) {
		super();
		MaBS = maBS;
		HoTen = hoTen;
		ChuyenMon = chuyenMon;
		DiaChi = diaChi;
		SDT = sDT;
		GioiTinh = gioiTinh;
		Email = email;
		NamKN = namKN;
		Image_BS = image_BS;
		Status = status;
	}

	public int getMaBS() {
		return MaBS;
	}

	public void setMaBS(int maBS) {
		MaBS = maBS;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public String getChuyenMon() {
		return ChuyenMon;
	}

	public void setChuyenMon(String chuyenMon) {
		ChuyenMon = chuyenMon;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getNamKN() {
		return NamKN;
	}

	public void setNamKN(int namKN) {
		NamKN = namKN;
	}

	public String getImage_BS() {
		return Image_BS;
	}

	public void setImage_BS(String image_BS) {
		Image_BS = image_BS;
	}

	public boolean getStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	@Override
	public String toString() {
		return this.getMaBS() + " - " + this.getHoTen();
	}
}
