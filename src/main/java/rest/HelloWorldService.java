package rest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class HelloWorldService {

    @GET
    //@Path("{name}")
    @Produces("application/json")
    //public String hello(@PathParam("name") String name) {
        public String hello() {
        //return "Hello " + name + "!";
        return "Hello rest!";
    }

}
