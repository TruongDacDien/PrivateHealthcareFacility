package models;

import java.math.BigDecimal;

public class XetNghiem {
    private int MaXN;
    private int MaPK;
    private int MaLoaiXN;
    private int MaBSXN;
    private String TenXN;
    private String KetQua;
    private String ChuanDoan;
    private BigDecimal ChiPhiXN;
    private boolean Status;

    public XetNghiem() {
        super();
    }

    public XetNghiem(int maXN, int maPK, int maLoaiXN, int maBSXN, String tenXN, String ketQua, String chuanDoan, BigDecimal chiPhiXN, boolean status) {
        super();
        MaXN = maXN;
        MaPK = maPK;
        MaLoaiXN = maLoaiXN;
        MaBSXN = maBSXN;
        TenXN = tenXN;
        KetQua = ketQua;
        ChuanDoan = chuanDoan;
        ChiPhiXN = chiPhiXN;
        Status = status;
    }

    public int getMaXN() {
        return MaXN;
    }

    public void setMaXN(int maXN) {
        MaXN = maXN;
    }

    public int getMaPK() {
        return MaPK;
    }

    public void setMaPK(int maPK) {
        MaPK = maPK;
    }

    public int getMaLoaiXN() {
        return MaLoaiXN;
    }

    public void setMaLoaiXN(int maLoaiXN) {
        MaLoaiXN = maLoaiXN;
    }

    public int getMaBSXN() {
        return MaBSXN;
    }

    public void setMaBSXN(int maBSXN) {
        MaBSXN = maBSXN;
    }

    public String getTenXN() {
        return TenXN;
    }

    public void setTenXN(String tenXN) {
        TenXN = tenXN;
    }

    public String getKetQua() {
        return KetQua;
    }

    public void setKetQua(String ketQua) {
        KetQua = ketQua;
    }

    public String getChuanDoan() {
        return ChuanDoan;
    }

    public void setChuanDoan(String chuanDoan) {
        ChuanDoan = chuanDoan;
    }

    public BigDecimal getChiPhiXN() {
        return ChiPhiXN;
    }

    public void setChiPhiXN(BigDecimal chiPhiXN) {
        ChiPhiXN = chiPhiXN;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "XetNghiem [MaXN=" + MaXN + ", MaPK=" + MaPK + ", MaLoaiXN=" + MaLoaiXN
                + ", MaBSXN=" + MaBSXN + ", TenXN=" + TenXN + ", KetQua=" + KetQua + ", ChuanDoan=" + ChuanDoan
                + ", ChiPhiXN=" + ChiPhiXN + ", Status=" + Status + "]";
    }
}
