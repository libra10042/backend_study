package co.kr.training.dto;

public class AreaSaleVO {

    private String areaCd;
    private String regionArea;
    private String prodId;
    private int saleCnt;

    public AreaSaleVO() {

    }

    public AreaSaleVO(String areaCd, String regionArea, String prodId, int saleCnt) {
        this.areaCd = areaCd;
        this.regionArea = regionArea;
        this.prodId = prodId;
        this.saleCnt = saleCnt;
    }

    public String getAreaCd() {
        return areaCd;
    }

    public String getRegionArea() {
        return regionArea;
    }

    public String getProdId() {
        return prodId;
    }

    public int getSaleCnt() {
        return saleCnt;
    }


    public void setAreaCd(String areaCd) {
        this.areaCd = areaCd;
    }

    public void setRegionArea(String regionArea) {
        this.regionArea = regionArea;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public void setSaleCnt(int saleCnt) {
        this.saleCnt = saleCnt;
    }

    @Override
    public String toString() {
        return "AreaSaleVO{" +
                "areaCd='" + areaCd + '\'' +
                ", regionArea='" + regionArea + '\'' +
                ", prodId='" + prodId + '\'' +
                ", saleCnt=" + saleCnt +
                '}';
    }
}
