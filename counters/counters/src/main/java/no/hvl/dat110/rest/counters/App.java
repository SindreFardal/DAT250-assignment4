package no.hvl.dat110.rest.counters;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.post;
import static spark.Spark.delete;
import java.util.*;


import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
	
	static Counters counters = null;
	static TODO todo = null;
	static HashMap<Long, TODO> todolist = null;

	public static void main(String[] args) {

		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}
		todo = new TODO();
		todolist = new HashMap<Long, TODO>();
		
		after((req, res) -> {
  		  res.type("application/json");
  		});


		get("/TODO/id", (req, res) -> {

			Long id = Long.parseLong(req.params("id"));

			return todolist.get(id).toJson();
		});

		get("/TODO", (req, res) -> {

			List<TODO> list = new ArrayList<TODO>(todolist.values());;

			Gson gson = new Gson();

			return gson.toJson(todolist);

		});
               
        put("/Counters", (req,res) -> {
        
        	Gson gson = new Gson();
        	
        	counters = gson.fromJson(req.body(), Counters.class);
        
            return counters.toJson();
        	
        });
		put("/TODO/:id", (req,res) -> {

			Gson gson = new Gson();

			todolist.put(Long.parseLong(req.params("id")), gson.fromJson(req.body(), TODO.class));

			return todolist.get(Long.parseLong(req.params("id"))).toJson();
		});
		post("/TODO", (req,res) -> {

			Gson gson = new Gson();

			todo = gson.fromJson(req.body(), TODO.class);

			todolist.put(todo.getId(), todo);

			return(todolist.values());
		});
		delete("/TODO/:id", (req, res) -> {

			Long id = Long.parseLong(req.params("id"));

			todolist.remove(id);

			return "Deleted";
		});
    }
    
}
