package Model;
public class UnidadesDto {
    private int unitid;
    private String unitName;
    private String rTime;
    private Integer rDistance;

    public UnidadesDto(int unitid, String unitName, String t, Integer d) {
        this.unitid = unitid;
        this.unitName = unitName;
        this.rTime = t;
        this.rDistance = d;
    }

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getrTime() {
        return rTime;
    }

    public void setrTime(String rTime) {
        this.rTime = rTime;
    }

    public Integer getrDistance() {
        return rDistance;
    }

    public void setrDistance(Integer rDistance) {
        this.rDistance = rDistance;
    }    
}
