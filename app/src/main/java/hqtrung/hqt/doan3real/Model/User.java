package hqtrung.hqt.doan3real.Model;

public class User {
    private String ten;
    private String diachi;
    private String matkhau;
    private String sodienthoai;
    private String email;

    public User() {
    }

    public User(String ten, String diachi, String matkhau, String sodienthoai, String email) {
        this.ten = ten;
        this.diachi = diachi;
        this.matkhau = matkhau;
        this.sodienthoai = sodienthoai;
        this.email = email;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
