package models;

import java.sql.Date;
import java.sql.Timestamp;

public class LichHen {
    private int MaLH;
    private int MaBS;
    private int MaBN;
    private Timestamp ThoiGianHen;
    private String TrangThai;
    private boolean Status;

    public LichHen() {
        super();
    }

    public LichHen(int maLH, int maBS, int maBN, Timestamp thoiGianHen, String trangThai, boolean status) {
        super();
        MaLH = maLH;
        MaBS = maBS;
        MaBN = maBN;
        ThoiGianHen = thoiGianHen;
        TrangThai = trangThai;
        Status = status;
    }

    public int getMaLH() {
        return MaLH;
    }

    public void setMaLH(int maLH) {
        MaLH = maLH;
    }

    public int getMaBS() {
        return MaBS;
    }
    public Date lichHen() {
    	return Date.valueOf(ThoiGianHen.toLocalDateTime().toLocalDate());
    }
    public void setMaBS(int maBS) {
        MaBS = maBS;
    }

    public int getMaBN() {
        return MaBN;
    }

    public void setMaBN(int maBN) {
        MaBN = maBN;
    }

    public Timestamp getThoiGianHen() {
        return ThoiGianHen;
    }

    public void setThoiGianHen(Timestamp thoiGianHen) {
        ThoiGianHen = thoiGianHen;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "LichHen [MaLH=" + MaLH + ", MaBS=" + MaBS + ", MaBN=" + MaBN + ", ThoiGianHen=" + ThoiGianHen + ", TrangThai=" + TrangThai + ", Status=" + Status + "]";
    }
}
