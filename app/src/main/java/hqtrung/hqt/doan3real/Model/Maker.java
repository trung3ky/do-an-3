package hqtrung.hqt.doan3real.Model;

public class Maker {
    private String nameMaker;
    private String key;
    private String soluong;

    public Maker(String nameMaker, String key) {
        this.nameMaker = nameMaker;
        this.key = key;
    }
    public Maker(String nameMaker, String key, String soluong) {
        this.nameMaker = nameMaker;
        this.key = key;
        this.soluong = soluong;
    }


    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNameMaker() {
        return nameMaker;
    }

    public void setNameMaker(String nameMaker) {
        this.nameMaker = nameMaker;
    }


}
