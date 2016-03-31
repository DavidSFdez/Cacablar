package uo.sdi.presentation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

@ManagedBean(name = "trips")
@RequestScoped
public class TripsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Trip> trips;

    private List<Trip> tripsToCancel;

    private boolean aux = false;

    public List<Trip> getTrips() {
	return trips;
    }

    public void setTrips(List<Trip> trips) {
	this.trips = trips;
    }

    public List<Trip> getTripsToCancel() {
	return tripsToCancel;
    }

    public void setTripsToCancel(List<Trip> tripsToCancel) {
	this.tripsToCancel = tripsToCancel;
    }

    public boolean isAux() {
	return aux;
    }

    public void setAux(boolean aux) {
	this.aux = aux;
    }

    public TripsBean() {
	trips = new LinkedList<>();
	tripsToCancel = new LinkedList<>();
    }

    public boolean value(Trip trip) {

	return tripsToCancel.contains(trip);
    }

    public String cancelTrips(long idUser) {

	try {
	    if (tripsToCancel != null && tripsToCancel.size() != 0)
		for (Trip trip : tripsToCancel)
		    Factories.services.createTripsService()
			    .cancel(trip, idUser);
	    tripsToCancel = new LinkedList<>();
	} catch (EntityNotFoundException e) {
	    // rollback?
	    return "fracaso";
	}

	return "exito";
    }

    public List<Trip> getListActive() {
	List<Trip> trips = null;
	try {
	    trips = Factories.services.createTripsService().listActive();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	this.trips = trips;
	return trips;
    }

    public List<Trip> listRelated(Long idUser) {
	List<Trip> trips = new LinkedList<Trip>();
	try {
	    trips = Factories.services.createTripsService().listRelated(idUser);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	this.trips = trips;
	return trips;
    }

    public List<Trip> getListActiveToUser(Long idUser) {
	List<Trip> trips = new LinkedList<Trip>();

	if (idUser == null) {
	    this.trips = trips;
	    return getListActive();
	}

	try {

	    trips = Factories.services.createTripsService().listActiveToUser(
		    idUser);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	this.trips = trips;
	return trips;
    }

}
