package hu.ulyssys.javaee.rest.endpoint;

import hu.ulyssys.javaee.entity.Courier;
import hu.ulyssys.javaee.rest.model.CourierModel;
import hu.ulyssys.javaee.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/courier")
public class CourierRestService extends CoreRestService<Courier, CourierModel> {
    @Inject
    private UserService userService;

    @Override
    protected Courier dtoToEntity(Courier courier, CourierModel courierModel) {
        courier.setCreatorUser(userService.findById(courierModel.getCreatorID()));
        if (courierModel.getModifiedDate() != null) {
            courier.setModifiedDate(courierModel.getModifiedDate());
        }
        if (courierModel.getModifiedID() != null) {
            courier.setModifiedUser(userService.findById(courierModel.getModifiedID()));
        }
        if (courierModel.isFirstNameSameLastName(courierModel.getLastName(), courierModel.getFirstName())) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        courier.setLastName(courierModel.getLastName());
        courier.setFirstName(courierModel.getFirstName());
        courier.setPhoneNumber(courierModel.getPhoneNumber());
        courier.setCreatedDate(courierModel.getCreatedDate());
        return courier;
    }

    @Override
    protected Class<Courier> getManagedClass() {
        return Courier.class;
    }

    @Override
    protected CourierModel entityToDTO(Courier courier) {
        CourierModel model = new CourierModel();
        model.setId(courier.getId());
        model.setCreatedDate(courier.getCreatedDate());
        model.setFirstName(courier.getFirstName());
        if (courier.getModifiedUser() != null) {
            model.setModifiedID(courier.getModifiedUser().getId());
        }
        if (courier.getModifiedDate() != null) {
            model.setModifiedDate(courier.getModifiedDate());
        }
        model.setCreatorID(courier.getCreatorUser().getId());
        model.setLastName(courier.getLastName());
        model.setPhoneNumber(courier.getPhoneNumber());
        return model;
    }
}
