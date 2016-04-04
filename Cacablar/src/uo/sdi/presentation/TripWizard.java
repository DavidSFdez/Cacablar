package uo.sdi.presentation;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.AddressPoint;
import uo.sdi.model.Trip;
import uo.sdi.model.Waypoint;

@ManagedBean(name = "wizard")
@ViewScoped
public class TripWizard implements Serializable {

    private static final long serialVersionUID = 1L;

    private Trip trip = new Trip();
    private String addressD;
    private String cityD;
    private String stateD;
    private String countryD;
    private String zipCodeD;
    private Double latD = 0D;
    private Double lonD = 0D;
    private String addressA;
    private String cityA;
    private String stateA;
    private String countryA;
    private String zipCodeA;
    private Double latA = 0D;
    private Double lonA = 0D;
    private boolean isUpdate = false;
    
    private String id;
    
    public String updateTripData() {

	if (id != null) {
	    try {
		this.trip = Factories.services.createTripsService().findById(Long.parseLong(id));
	    } catch (NumberFormatException | EntityNotFoundException e) {

	    }

	    this.addressD = trip.getDeparture().getAddress();
	    this.cityD = trip.getDeparture().getCity();
	    this.countryD = trip.getDeparture().getCountry();
	    this.latD = trip.getDeparture().getWaypoint().getLat();
	    this.lonD = trip.getDeparture().getWaypoint().getLon();
	    this.stateD = trip.getDeparture().getState();
	    this.zipCodeD = trip.getDeparture().getZipCode();

	    this.addressA = trip.getDestination().getAddress();
	    this.cityA = trip.getDestination().getCity();
	    this.countryA = trip.getDestination().getCountry();
	    this.latA = trip.getDestination().getWaypoint().getLat();
	    this.lonA = trip.getDestination().getWaypoint().getLon();
	    this.stateA = trip.getDestination().getState();
	    this.zipCodeA = trip.getDestination().getZipCode();

	    this.isUpdate = true;

	    return "exito";
	}
	return "fracaso";
    }

    

    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }



    public boolean isUpdate() {
	return isUpdate;
    }

    public void setUpdate(boolean isUpdate) {
	this.isUpdate = isUpdate;
    }

    public Trip getTrip() {
	return trip;
    }

    public void setUser(Trip trip) {
	this.trip = trip;
    }

    public String update(Long idUser) {

	try {

	    AddressPoint departure = new AddressPoint(addressD, cityD, stateD,
		    countryD, zipCodeD, new Waypoint(latD, lonD));
	    AddressPoint destination = new AddressPoint(addressA, cityA,
		    stateA, countryA, zipCodeA, new Waypoint(latA, lonA));

	    trip.setDeparture(departure);
	    trip.setDestination(destination);

	    Factories.services.createTripsService().update(trip, idUser);
	    this.isUpdate = false;

	} catch (EntityNotFoundException e) {
	    return "false";
	}

	return "exito";
    }

    public String save(Long idUser) {
	AddressPoint departure = new AddressPoint(addressD, cityD, stateD,
		countryD, zipCodeD, new Waypoint(latD, lonD));
	AddressPoint destination = new AddressPoint(addressA, cityA, stateA,
		countryA, zipCodeA, new Waypoint(latA, lonA));

	trip.setDeparture(departure);
	trip.setDestination(destination);

	try {
	    Factories.services.createTripsService().save(trip, idUser);
	} catch (EntityAlreadyExistsException e) {

	    return "fracaso";
	}

	return "exito";
    }

    public String onFlowProcess(FlowEvent event) {

	return event.getNewStep();

    }

    public Date getToday() {
	return new Date();
    }

    public String getAddressD() {
	return addressD;
    }

    public void setAddressD(String addressD) {
	this.addressD = addressD;
    }

    public String getCityD() {
	return cityD;
    }

    public void setCityD(String cityD) {
	this.cityD = cityD;
    }

    public String getStateD() {
	return stateD;
    }

    public void setStateD(String stateD) {
	this.stateD = stateD;
    }

    public String getCountryD() {
	return countryD;
    }

    public void setCountryD(String countryD) {
	this.countryD = countryD;
    }

    public String getZipCodeD() {
	return zipCodeD;
    }

    public void setZipCodeD(String zipCodeD) {
	this.zipCodeD = zipCodeD;
    }

    public Double getLatD() {
	return latD;
    }

    public void setLatD(Double latD) {
	this.latD = latD;
    }

    public Double getLonD() {
	return lonD;
    }

    public void setLonD(Double lonD) {
	this.lonD = lonD;
    }

    public String getAddressA() {
	return addressA;
    }

    public void setAddressA(String addressA) {
	this.addressA = addressA;
    }

    public String getCityA() {
	return cityA;
    }

    public void setCityA(String cityA) {
	this.cityA = cityA;
    }

    public String getStateA() {
	return stateA;
    }

    public void setStateA(String stateA) {
	this.stateA = stateA;
    }

    public String getCountryA() {
	return countryA;
    }

    public void setCountryA(String countryA) {
	this.countryA = countryA;
    }

    public String getZipCodeA() {
	return zipCodeA;
    }

    public void setZipCodeA(String zipCodeA) {
	this.zipCodeA = zipCodeA;
    }

    public Double getLatA() {
	return latA;
    }

    public void setLatA(Double latA) {
	this.latA = latA;
    }

    public Double getLonA() {
	return lonA;
    }

    public void setLonA(Double lonA) {
	this.lonA = lonA;
    }

    public void setTrip(Trip trip) {
	this.trip = trip;
    }

}
