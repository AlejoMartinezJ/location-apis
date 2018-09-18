package com.tesis.apis.locationmaps.service;

import Model.Position;
import Model.TimeDriving;
import com.tesis.apis.locationmaps.entity.Location;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.web.client.RestClientException;

public interface LocationService {
    Position findLocationAddress(String address) throws RestClientException, UnsupportedEncodingException;
    TimeDriving findTimeDriveBetweenTwoPoint(String latA, String lngA, String latB, String lngB) throws RestClientException, UnsupportedEncodingException;
    Integer[][] buildMatrixOfTime(List<String> positions, String placeBase);
    List<Location> getAllHotSpots();
    void deleteHotSpot(Integer id);
    Location saveHotSpot(Location location);
}
