package uo.sdi.business;

import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.model.Application;

public interface ApplicationService {

    Application find(Long idTrip, Long idUser) throws EntityNotFoundException;

}
