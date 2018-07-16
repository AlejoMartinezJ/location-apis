package Model;

public class TimeDriving { 
    private String origin;
    private String destination;
    private Integer time;
    private Integer distance;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public TimeDriving(String origin, String destination, Integer time, Integer distance) {
        this.origin = origin;
        this.destination = destination;
        this.time = time;
        this.distance = distance;
    }
    
    public TimeDriving(){
        this.time = 0;
        this.distance = 0;
    }
    
}
