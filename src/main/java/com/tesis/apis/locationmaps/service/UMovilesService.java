package com.tesis.apis.locationmaps.service;

import Model.TimeDriving;
import com.tesis.apis.locationmaps.entity.Spots;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.web.client.RestClientException;

public interface UMovilesService {
    List<Spots> buildMatrixOfTime(List<Spots> positions);
}
