package co.kr.training.dto;

public class Msales {
    private String prodId;
    private String prodnm;
    private String saleCnt;


    public Msales() {
    }

    public Msales(String prodId, String prodnm, String saleCnt) {
        this.prodId = prodId;
        this.prodnm = prodnm;
        this.saleCnt = saleCnt;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdnm() {
        return prodnm;
    }

    public void setProdnm(String prodnm) {
        this.prodnm = prodnm;
    }

    public String getSaleCnt() {
        return saleCnt;
    }

    public void setSaleCnt(String saleCnt) {
        this.saleCnt = saleCnt;
    }

    @Override
    public String toString() {
        return "Msales{" +
                "prodId='" + prodId + '\'' +
                ", prodnm='" + prodnm + '\'' +
                ", saleCnt='" + saleCnt + '\'' +
                '}';
    }
}
