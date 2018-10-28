package Model;
public class UnidadesDto {
    private int unitid;
    private String unitName;
    private String placeName;
    private String status;
    private String lat;
    private String lng;

    public UnidadesDto(int unitid, String unitName, String placeName,String status) {
        this.unitid = unitid;
        this.unitName = unitName;
        this.placeName = placeName;
        this.status = status;
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

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
    
}
