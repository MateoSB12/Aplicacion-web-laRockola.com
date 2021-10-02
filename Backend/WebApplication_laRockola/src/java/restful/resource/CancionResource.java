package restful.resource;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import restful.Model.CancionModel;
import restful.service.CancionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;

@Path("canciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CancionResource {
    CancionService servicio = new CancionService();

    @GET
    public ArrayList<CancionModel> getCanciones() {
        return servicio.getCanciones();
    }
    @Path("/{CancionId}")
    @GET
    public CancionModel getCancion(@PathParam("CancionId") String id) {

        return servicio.getCancion(id);
    }    
    @POST
    public CancionModel addCancion(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        CancionModel cancion = gson.fromJson(JSON, CancionModel.class);
        return servicio.addCancion(cancion);
    }
    
    
    @DELETE
    @Path("/{CancionId}")
    public String delCancion(@PathParam("CancionId") String id) {

        return servicio.delCancion(id);

    }

    @PUT
    public CancionModel updateCancion(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        CancionModel cancion = gson.fromJson(JSON, CancionModel.class);
        return servicio.updateCancion(cancion);
    }    
    

    
}

