package hqtrung.hqt.doan3real.Model;

public class LatLgn {
    private String VyDo;
    private String KinhDo;

    public LatLgn(String vyDo, String kinhDo) {
        VyDo = vyDo;
        KinhDo = kinhDo;
    }

    public String getVyDo() {
        return VyDo;
    }

    public void setVyDo(String vyDo) {
        VyDo = vyDo;
    }

    public String getKinhDo() {
        return KinhDo;
    }

    public void setKinhDo(String kinhDo) {
        KinhDo = kinhDo;
    }
}
