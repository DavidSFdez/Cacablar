package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.TripsService;
import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.classes.trip.TripsCancel;
import uo.sdi.business.impl.classes.trip.TripsListActive;
import uo.sdi.business.impl.classes.trip.TripsListActiveToUser;
import uo.sdi.business.impl.classes.trip.TripsListRelated;
import uo.sdi.business.impl.classes.trip.TripsSave;
import uo.sdi.business.impl.classes.trip.TripsUpdate;
import uo.sdi.model.Trip;

public class SimpleTripsService implements TripsService {

    @Override
    public List<Trip> listActive() throws Exception {
	return new TripsListActive().list();
    }

    @Override
    public List<Trip> listRelated(Long idUser) throws Exception {
	
	return new TripsListRelated().list(idUser);
    }

    @Override
    public List<Trip> listActiveToUser(Long idUser) throws Exception {
	
	return new TripsListActiveToUser().list(idUser);
    }
    
    @Override
    public void update(Trip trip,Long idUser) throws EntityNotFoundException{

	new TripsUpdate().update(trip,idUser);
	
    }

    @Override
    public void cancel(Trip trip,Long idUser) throws EntityNotFoundException {
	new TripsCancel().cancel(trip,idUser);
	
    }

    @Override
    public void save(Trip trip,Long idUser) throws EntityAlreadyExistsException {
	new TripsSave().save(trip,idUser);
	
    }

  

}
