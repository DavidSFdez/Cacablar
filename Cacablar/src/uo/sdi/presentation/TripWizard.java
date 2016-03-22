package uo.sdi.presentation;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import uo.sdi.model.AddressPoint;
import uo.sdi.model.Trip;
import uo.sdi.model.Waypoint;

@ManagedBean(name = "wizard")
@ViewScoped
public class TripWizard implements Serializable {

    private Trip trip = new Trip();
    private String addressD;
    private String cityD;
    private String stateD;
    private String countryD;
    private String zipCodeD;
    private Double latD=0D;
    private Double lonD=0D;
    private String addressA;
    private String cityA;
    private String stateA;
    private String countryA;
    private String zipCodeA;
    private Double latA=0D;
    private Double lonA=0D;

    public Trip getTrip() {
	return trip;
    }

    public void setUser(Trip trip) {
	this.trip = trip;
    }

    public void save(Long UserId) {
	//TODO 
	AddressPoint departure = new AddressPoint(addressD, cityD, stateD, countryD,zipCodeD, new Waypoint(latD, lonD));
	AddressPoint destination = new AddressPoint(addressA, cityA, stateA, countryA,zipCodeA, new Waypoint(latA, lonA));
	
	trip.setDeparture(departure);
	trip.setDestination(destination);
	
	FacesMessage msg = new FacesMessage("Successful", "Welcome :");
	FacesContext.getCurrentInstance().addMessage(null, msg);
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
