package uo.sdi.business;

import java.util.List;

import uo.sdi.model.Trip;

public interface TripsService {

    List<Trip> listActive();
    List<Trip> listRelated(Long idUser);
    void updateTrip(Long idUser);
    void cancelTrip(Long idUser);
    void saveTrip(Long idUser);
}
