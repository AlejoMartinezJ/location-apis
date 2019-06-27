package Model;

public class Position {
    
    private Integer locationid;
    private String lat;
    private String lng;
    private Integer order;
    private String placeName;
    private List<boolean> locationStatus;

    public Position(Integer locationid, String lat, String lng, Integer order, String name) {
        this.locationid = locationid;
        this.lat = lat;
        this.lng = lng;
        this.order = order;
        this.placeName = name;
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

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    public List<String> getLocationStatus(){
        if (this.placeName != null){
            return locationStatus;
        }
    }

}
