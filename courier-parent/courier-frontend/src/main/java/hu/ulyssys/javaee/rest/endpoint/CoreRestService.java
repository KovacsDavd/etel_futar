package hu.ulyssys.javaee.rest.endpoint;

import hu.ulyssys.javaee.entity.CoreEntity;
import hu.ulyssys.javaee.rest.model.CoreModel;
import hu.ulyssys.javaee.rest.request.RestFunctionRequest;
import hu.ulyssys.javaee.rest.response.RestFindAllResponse;
import hu.ulyssys.javaee.rest.response.RestFindByIdResponse;
import hu.ulyssys.javaee.service.CoreService;
import hu.ulyssys.javaee.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public abstract class CoreRestService<T extends CoreEntity, M extends CoreModel> {
    @Inject
    private CoreService<T> coreService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        T entity = coreService.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new RestFindByIdResponse<>(entityToDTO(entity))).build();
    }


    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        List<M> list = new ArrayList<>();
        coreService.getAll().forEach(entity -> {
            list.add(entityToDTO(entity));
        });

        return Response.ok(new RestFindAllResponse<>(list)).build();
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid RestFunctionRequest<M> request) {
        if (request.getModel() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        T entity = null;
        try {
            entity = dtoToEntity(getManagedClass().newInstance(), request.getModel());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        coreService.add(entity);

        return Response.ok(new RestFindByIdResponse<>(entityToDTO(entity))).build();

    }

    @PUT
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid RestFunctionRequest<M> request) {
        if (request.getModel() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        T entity = coreService.findById(request.getModel().getId());
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dtoToEntity(entity, request.getModel());
        coreService.update(entity);

        return Response.ok(new RestFindByIdResponse<>(entityToDTO(entity))).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") Long id) {
        T entity = coreService.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        coreService.remove(entity);
        return Response.ok().build();
    }

    protected abstract M entityToDTO(T entity);

    protected abstract T dtoToEntity(T entity, M model);

    protected abstract Class<T> getManagedClass();

}
