package Model;

public class Position {
    
    private String address;
    private String lat;
    private String lng;
    private Integer order;

    public Position(String address, String lat, String lng, Integer order) {
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.order = order;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
