package uo.sdi.business;

import java.util.List;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.Trip;

public interface TripsService {

    List<Trip> listActive() throws Exception;
    List<Trip> listRelated(Long idUser) throws Exception;
    List<Trip> listActiveToUser(Long idUser) throws Exception;
    void update(Trip trip, Long idUser) throws EntityNotFoundException;
    void cancel(Trip trip,Long idUser) throws EntityNotFoundException;
    void save(Trip trip,Long idUser) throws EntityAlreadyExistsException;
    
   
}
