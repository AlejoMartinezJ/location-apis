package Model;

public class TimeDriving { 
    private String origin;
    private String destination;
    private Double time;
    private Double distance;

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

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public TimeDriving(String origin, String destination, Double time, Double distance) {
        this.origin = origin;
        this.destination = destination;
        this.time = time;
        this.distance = distance;
    }
    
    public TimeDriving(){
        
    }
    
}
