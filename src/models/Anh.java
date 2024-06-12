package models;

public class Anh {
    private int MaAnh;
    private int MaKQXN;  // Foreign key reference to KETQUAXETNGHIEM
    private String DuongDan;
    private boolean Status;

    public Anh() {
        super();
    }

    public Anh(int maAnh, int maKQXN, String duongDan, boolean status) {
        super();
        MaAnh = maAnh;
        MaKQXN = maKQXN;
        DuongDan = duongDan;
        Status = status;
    }

    public int getMaAnh() {
        return MaAnh;
    }

    public void setMaAnh(int maAnh) {
        MaAnh = maAnh;
    }

    public int getMaKQXN() {
        return MaKQXN;
    }

    public void setMaKQXN(int maKQXN) {
        MaKQXN = maKQXN;
    }

    public String getDuongDan() {
        return DuongDan;
    }

    public void setDuongDan(String duongDan) {
        DuongDan = duongDan;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Anh [MaAnh=" + MaAnh + ", MaKQXN=" + MaKQXN + ", DuongDan=" + DuongDan + ", Status=" + Status + "]";
    }
}
