package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.TripsService;
import uo.sdi.business.impl.classes.trip.TripsListActive;
import uo.sdi.model.Trip;

public class SimpleTripsService implements TripsService {

    @Override
    public List<Trip> listActive() {
	return new TripsListActive().list();
    }

    @Override
    public List<Trip> listRelated(Long idUser) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void updateTrip(Long idUser) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void cancelTrip(Long idUser) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void saveTrip(Long idUser) {
	// TODO Auto-generated method stub
	
    }

}
