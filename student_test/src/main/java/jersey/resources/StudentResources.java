package jersey.resources;

import jersey.config.MariaConnection;
import jersey.domain.Student;
import jersey.services.StudentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/students")
public class StudentResources {
    private StudentService st = new StudentService(new MariaConnection().getConnection());

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> findAll() {
        return st.selectAll();
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student findById(@PathParam("id") int id) {
        return st.selectById(id);
    }

    @POST
    @Path("/add")
//    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String create(Student student) {
        int i = st.insert(student);
        return (i > 0 ? "Success" : "Fail");
    }

    @PUT
    @Path("/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(@PathParam("id") int id, Student student) {
        int i = st.updateById(id, student);
        return (i > 0 ? "Success" : "Fail");
    }

    @DELETE
    @Path("id/{id}")
    public  String delete(@PathParam("id") int id) {
        return (st.deleteById(id) ? "Success" : "Fail");
    }

}
