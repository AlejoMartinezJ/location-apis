package Model;

public class Position {
    
    private Integer locationid;
    private String lat;
    private String lng;
    private Integer order;

    public Position(Integer locationid, String lat, String lng, Integer order) {
        this.locationid = locationid;
        this.lat = lat;
        this.lng = lng;
        this.order = order;
    }

    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }
    
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    public Position() {
    }

}
