package hqtrung.hqt.doan3real.Model;

public class BaiDoXe {
    private String hinh_anh;
    private String ten;
    private String key;
    private String DiaChi;
    private String kinhDo, viDo, sdt, soluong, gio, conlai;

    public BaiDoXe() {
    }

    public BaiDoXe(String diaChi, String sdt, String kinhDo, String hinh_anh, String ten, String viDo, String gio, String soluong) {
        DiaChi = diaChi;
        this.sdt = sdt;
        this.kinhDo = kinhDo;
        this.hinh_anh = hinh_anh;
        this.ten = ten;
        this.viDo = viDo;
        this.gio = gio;
        this.soluong = soluong;
    }

    public BaiDoXe(String hinh_anh, String ten, String key, String diaChi, String kinhDo, String viDo, String sdt, String soluong, String gio) {
        this.hinh_anh = hinh_anh;
        this.ten = ten;
        this.key = key;
        DiaChi = diaChi;
        this.kinhDo = kinhDo;
        this.viDo = viDo;
        this.sdt = sdt;
        this.soluong = soluong;
        this.gio = gio;
    }
    public BaiDoXe(String hinh_anh, String ten, String key, String diaChi, String kinhDo, String viDo, String sdt, String soluong, String gio,String conlai) {
        this.hinh_anh = hinh_anh;
        this.ten = ten;
        this.key = key;
        DiaChi = diaChi;
        this.kinhDo = kinhDo;
        this.viDo = viDo;
        this.sdt = sdt;
        this.soluong = soluong;
        this.gio = gio;
        this.conlai = conlai;
    }

    public BaiDoXe(String hinh_anh, String ten, String key, String diaChi) {
        this.hinh_anh = hinh_anh;
        this.ten = ten;
        this.key = key;
        DiaChi = diaChi;
    }

    public String getConlai() {
        return conlai;
    }

    public void setConlai(String conlai) {
        this.conlai = conlai;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public String getKinhDo() {
        return kinhDo;
    }

    public void setKinhDo(String kinhDo) {
        this.kinhDo = kinhDo;
    }

    public String getViDo() {
        return viDo;
    }

    public void setViDo(String viDo) {
        this.viDo = viDo;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }



    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHinh_anh() {
        return hinh_anh;
    }

    public void setHinh_anh(String hinh_anh) {
        this.hinh_anh = hinh_anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
