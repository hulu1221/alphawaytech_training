package jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HelloJersey {

    @GET
    @Path("/")
    public String hello() {
        return "This is my first java api project with Jersey!";
    }
}
