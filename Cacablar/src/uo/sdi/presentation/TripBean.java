package uo.sdi.presentation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

@ManagedBean(name="trip")
@RequestScoped
public class TripBean implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    Trip trip;
    
    public TripBean(){
	trip = new Trip();
    }
    
   public List<Trip> getListActive(){
       List<Trip> trips = null;
       trips = Factories.services.createTripsService().listActive();
       return trips;
   }
   
   public List<Trip> getListRelated(Long idUser){
       List<Trip> trips = new LinkedList<Trip>();
       
       return trips;
   }
   
   public String updateTrip(Long idUser){
       List<Trip> trips = new LinkedList<Trip>();
       //TODO comprobar que el usuario sea promotor

       return "fracaso";
   }
   public String cancelTrip(Long idUser){
       List<Trip> trips = new LinkedList<Trip>();
       //TODO comprobar que el usuario sea promotor
       return "fracaso";
   }
   public String saveTrip(Long idUser){
       List<Trip> trips = new LinkedList<Trip>();
       //TODO comprobar que el usuario existe
       return "fracaso";
   }
   
   
   
}
