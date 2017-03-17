package demo.microprofile.tomeeweb;

import java.util.List;
import java.util.Map;
import java.io.InputStream;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Produces({MediaType.APPLICATION_JSON})
@Path("/expenses")
public class ExpenseEndpoint {
	
	private Map<String, List<Expense>> expenses;
	
    @GET
	@Path("{customerId}")
    public List<Expense> getExpenses(@PathParam("customerId") String customerId) throws Exception {
		expenses = loadExpenses();
        return expenses.get(customerId);
    }
	
	private Map<String, List<Expense>> loadExpenses() throws Exception {
        ObjectMapper om = new ObjectMapper();
        InputStream is = this.getClass().getResourceAsStream("/data.json");
        return om.readValue(is, new TypeReference<Map<String, List<Expense>>>(){});
	}
	
}