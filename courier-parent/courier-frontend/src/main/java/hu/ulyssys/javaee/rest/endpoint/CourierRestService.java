package hu.ulyssys.javaee.rest.endpoint;

import hu.ulyssys.javaee.entity.Courier;
import hu.ulyssys.javaee.entity.Order;
import hu.ulyssys.javaee.rest.model.CourierModel;
import hu.ulyssys.javaee.rest.request.RestFunctionRequest;
import hu.ulyssys.javaee.rest.response.RestFindAllResponse;
import hu.ulyssys.javaee.rest.response.RestFindByIdResponse;
import hu.ulyssys.javaee.service.CourierService;
import hu.ulyssys.javaee.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/courier")
public class CourierRestService {
    @Inject
    private CourierService courierService;
    @Inject
    private UserService userService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {

        Courier courier = courierService.findById(id);
        if (courier == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new RestFindByIdResponse<>(entityToDTO(courier))).build();
    }


    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        List<CourierModel> list = new ArrayList<>();
        courierService.getAll().forEach(courier -> {
            list.add(entityToDTO(courier));
        });

        return Response.ok(new RestFindAllResponse<>(list)).build();
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid RestFunctionRequest<CourierModel> request) {
        if (request.getModel() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Courier courier = dtoToEntity(new Courier(), request.getModel());
        courierService.add(courier);

        return Response.ok(new RestFindByIdResponse<>(entityToDTO(courier))).build();

    }

    @PUT
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid RestFunctionRequest<CourierModel> request) {
        if (request.getModel() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Courier courier = courierService.findById(request.getModel().getId());
        if (courier == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dtoToEntity(courier, request.getModel());
        courierService.update(courier);

        return Response.ok(new RestFindByIdResponse<>(entityToDTO(courier))).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") Long id) {
        Courier courier = courierService.findById(id);
        if (courier == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        courierService.remove(courier);
        return Response.ok().build();
    }


    private Courier dtoToEntity(Courier courier, CourierModel courierModel) {
        courier.setCreatorUser(userService.findById(courierModel.getCreatorID()));
        if (courierModel.getModifiedDate() != null) {
            courier.setModifiedDate(courierModel.getModifiedDate());
        }
        if (courierModel.getModifiedID() != null) {
            courier.setModifiedUser(userService.findById(courierModel.getModifiedID()));
        }
        courier.setLastName(courierModel.getLastName());
        courier.setFirstName(courierModel.getFirstName());
        courier.setPhoneNumber(courierModel.getPhoneNumber());
        courier.setCreatedDate(courierModel.getCreatedDate());
        return courier;
    }

    private CourierModel entityToDTO(Courier courier) {
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
