package uo.sdi.business;

import java.util.List;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;

public interface ApplicationService {

    Application find(Long idTrip, Long idUser) throws EntityNotFoundException;

    void remove(Long idUser, Long idTrip) throws EntityNotFoundException;

    List<Application> getToUpdate();

}
