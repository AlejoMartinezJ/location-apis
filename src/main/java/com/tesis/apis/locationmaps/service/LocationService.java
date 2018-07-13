package com.tesis.apis.locationmaps.service;

import Model.Position;
import java.io.UnsupportedEncodingException;
import org.springframework.web.client.RestClientException;

public interface LocationService {
    Position findLocationAddress(String address) throws RestClientException, UnsupportedEncodingException;
}
