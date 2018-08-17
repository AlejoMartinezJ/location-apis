
package com.tesis.apis.locationmaps.service;

import Model.Position;
import java.util.List;

public interface PositionService {
    List<Position> getUIPositionRoute(List<Integer> model);
}
