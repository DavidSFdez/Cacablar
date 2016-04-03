package bugs;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;


public class DebugCancelSeat {
    
    public static void main(String[] a){
	
	Seat seat = new Seat();
	seat.setTripId(201L);
	seat.setUserId(105L);
	seat.setStatus(SeatStatus.ADMITIDO);
	
	System.out.println(seat);
	
	try {
	    seat = Factories.services.createSeatsService().findByUserAndTrip(seat.getUserId(), seat.getTripId());
	} catch (EntityNotFoundException e) {
	    System.err.println("No encuentra primero.");
	}
	
	System.out.println(seat);
	
	try {
	    Factories.services.createSeatsService().cancelSeat(seat);
	} catch (EntityNotFoundException e) {
	    System.err.println("No encuentra primero.");
	}
	
	try {
	    seat = Factories.services.createSeatsService().findByUserAndTrip(seat.getUserId(), seat.getTripId());
	} catch (EntityNotFoundException e) {
	    System.err.println("No encuentra primero.");
	}
	
	System.out.println(seat);
	
	
    }

}
