package app;

import static spark.Spark.*;

public class Aplicacao {

	private static CarrinhosService carrinhosService = new CarrinhosService(); 
		
	public static void main(String[] args) {
		port(6789);
		
		post("/Carrinhos", (request, response) -> CarrinhosService.add(request, response));
		
		get("/Carrinhos/:id", (request, response) -> CarrinhosService.get(request, response));
		
		get("/Carrinhos/update/:id", (request, response) -> CarrinhosService.update(request, response));
		
		get("/Carrinhos/delete/:id", (request, response) -> CarrinhosService.remove(request, response));
		
		get("/Carrinhos", (request, response) -> CarrinhosService.getALL(request, response));
	}
	
}
