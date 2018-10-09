
package Model;

import com.google.maps.model.LatLng;

public class Coord {
    private String place;
    private double lat;
    private double lng;
    public Coord(String name,LatLng x){
		this.place=name;
		this.lat=x.lat;
		this.lng=x.lng;
   }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Coord{" + "place=" + place + ", lat=" + lat + ", lng=" + lng + '}';
    }
   
}
