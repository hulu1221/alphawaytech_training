package jersey.resources;

import com.google.gson.Gson;
import jersey.config.MariaConnection;
import jersey.domain.Student;
import jersey.services.StudentService;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/students")
public class StudentResources {
    private MariaConnection conn = new MariaConnection();
    private StudentService st = new StudentService(conn.getConnection("jdbc:mariadb://222.252.25.241:23306/06102020", "test", "123456"));

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
