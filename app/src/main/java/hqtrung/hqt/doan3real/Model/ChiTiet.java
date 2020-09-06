package hqtrung.hqt.doan3real.Model;

public class ChiTiet {
    private String keyBaiDauXe;
    private String trangThai;


    public ChiTiet() {
    }

    public ChiTiet(String keyBaiDauXe, String trangThai) {
        this.keyBaiDauXe = keyBaiDauXe;
        this.trangThai = trangThai;
    }

    public String getKeyBaiDauXe() {
        return keyBaiDauXe;
    }

    public void setKeyBaiDauXe(String keyBaiDauXe) {
        this.keyBaiDauXe = keyBaiDauXe;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }


}
