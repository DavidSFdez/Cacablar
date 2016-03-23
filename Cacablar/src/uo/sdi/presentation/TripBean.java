package uo.sdi.presentation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

@ManagedBean(name = "trip")
@RequestScoped
public class TripBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    Trip trip;

    public TripBean() {
	trip = new Trip();
    }

    public List<Trip> getListActive() {
	List<Trip> trips = null;
	try {
	    trips = Factories.services.createTripsService().listActive();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return trips;
    }

    public List<Trip> listRelated(Long idUser) {
	List<Trip> trips = new LinkedList<Trip>();
	try {
	    trips = Factories.services.createTripsService().listRelated(idUser);
	} catch (Exception e) {

	    e.printStackTrace();
	}
	return trips;
    }

    public boolean isPromoter(Long idTrip, Long idUser) {
	return Factories.services.createTripsService().findByIdandPromoter(
		idTrip, idUser) == null ? false : true;
    }

    public boolean isSitting(Long idTrip, Long idUser) {
	return Factories.services.createSeatsService().findByUserAndTrip(
		idTrip, idUser) == null ? false : true;
    }

    public List<Trip> getListActiveToUser(Long idUser) {
	List<Trip> trips = new LinkedList<Trip>();
	try {
	    trips = Factories.services.createTripsService().listActiveToUser(
		    idUser);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return trips;
    }

    public String updateTrip(Long idUser) {

	try {
	    Factories.services.createTripsService().update(trip, idUser);
	} catch (EntityNotFoundException e) {
	    return "fracaso";
	}

	return "exito";
    }

    public String cancelTrip(Long idUser) {

	try {
	    Factories.services.createTripsService().cancel(trip, idUser);
	} catch (EntityNotFoundException e) {
	    return "fracaso";
	}
	return "exito";
    }

    public String saveTrip(Long idUser) {
	try {
	    Factories.services.createTripsService().save(trip, idUser);
	} catch (EntityAlreadyExistsException e) {
	    return "fracaso";
	}
	return "exito";
    }

}
