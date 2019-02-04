package metube.domein.service;

import metube.domein.models.TubeServiceModel;
import metube.domein.models.view.TubeDetailsViewModel;

import java.util.List;

public interface TubeService {
    void saveTube(TubeServiceModel tubeServiceModel);
    TubeServiceModel findTubeByName(String name);
    List<TubeServiceModel> findAllTubes();
}
