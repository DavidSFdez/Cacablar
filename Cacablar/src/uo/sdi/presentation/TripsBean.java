package uo.sdi.presentation;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

@ManagedBean(name = "trips")
@RequestScoped
public class TripsBean implements Serializable {

    private Set<Trip> trips;

    public TripsBean() {
	trips = new HashSet<>();
    }

    public void cancel(Trip trip) {

	if (!trips.add(trip))
	    trips.remove(trip);

    }

    public boolean value(Trip trip) {
	return trips.contains(trip);
    }

    public String cancelTrips(long idUser) {

	try {
	    for (Trip trip : trips)
		Factories.services.createTripsService().cancel(trip, idUser);
	} catch (EntityNotFoundException e) {
	    // rollback?
	    return "fracaso";
	}

	return "exito";
    }
}
