package models;

import java.sql.Date;

public class KetQuaXetNghiem {
    private int MaKQXN;
    private int MaXN;        // Field for MaXN
    private String MoTa;
    private String KetLuan;
    private String LoiKhuyen;
    private Date NgayXN;     // Field for the date

    public KetQuaXetNghiem() {
        super();
    }

    public KetQuaXetNghiem(int maKQXN, int maXN, String moTa, String ketLuan, String loiKhuyen, Date ngayXN) {
        super();
        MaKQXN = maKQXN;
        MaXN = maXN;
        MoTa = moTa;
        KetLuan = ketLuan;
        LoiKhuyen = loiKhuyen;
        NgayXN = ngayXN;
    }

    public int getMaKQXN() {
        return MaKQXN;
    }

    public void setMaKQXN(int maKQXN) {
        MaKQXN = maKQXN;
    }

    public int getMaXN() {   // Getter for MaXN
        return MaXN;
    }

    public void setMaXN(int maXN) {   // Setter for MaXN
        MaXN = maXN;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getKetLuan() {
        return KetLuan;
    }

    public void setKetLuan(String ketLuan) {
        KetLuan = ketLuan;
    }

    public String getLoiKhuyen() {
        return LoiKhuyen;
    }

    public void setLoiKhuyen(String loiKhuyen) {
        LoiKhuyen = loiKhuyen;
    }

    public Date getNgayXN() {
        return NgayXN;
    }

    public void setNgayXN(Date ngayXN) {
        NgayXN = ngayXN;
    }

    @Override
    public String toString() {
        return "KetQuaXetNghiem [MaKQXN=" + MaKQXN + ", MaXN=" + MaXN + ", MoTa=" + MoTa + ", KetLuan=" + KetLuan
                + ", LoiKhuyen=" + LoiKhuyen + ", NgayXN=" + NgayXN + "]";
    }
}
