package com.tesis.apis.locationmaps.service;

import Model.Position;
import Model.TimeDriving;
import com.google.maps.errors.ApiException;
import com.tesis.apis.locationmaps.entity.Location;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.web.client.RestClientException;

public interface LocationService {
    Position findLocationAddress(String address) throws ApiException, InterruptedException, IOException, ClassNotFoundException;
    List<Location> getAllHotSpots();
    void deleteHotSpot(Integer id);
    Location saveHotSpot(Location location);
    Location findByLocationID(Integer location);
    TimeDriving findTimeDriveBetweenTwoPoint(String origen, String destination) throws ApiException, InterruptedException, IOException, ClassNotFoundException;

}
