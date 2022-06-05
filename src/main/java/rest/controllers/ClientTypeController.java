package rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import rest.model.ClientType;
import rest.model.ClientTypeModel;
import rest.model.ClientTypeModelAssembler;
import rest.service.ClientTypeService;

@RestController
@RequestMapping(path="/types", produces = "application/json")
public class ClientTypeController {

	private final ClientTypeService typeService;
	
	@Autowired
	public ClientTypeController(ClientTypeService typeService) {
		
		this.typeService=typeService;
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ClientType clientType) {
		
	   typeService.createType(clientType);
	   return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ClientType>> read() {
		
	   final List<ClientType> clientTypes = typeService.readAll();

	   return clientTypes != null &&  !clientTypes.isEmpty()
	           ? new ResponseEntity<>(clientTypes, HttpStatus.OK)
	           : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientType> read(@PathVariable(name = "id") int id) {
		
	   final ClientType clientType = typeService.readOne(id);

	   return clientType != null
	           ? new ResponseEntity<>(clientType, HttpStatus.OK)
	           : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

	/*
	 * HATEOS implementation
	 * 
	 */
	
	@GetMapping("/typesh")
	public CollectionModel<EntityModel<ClientType>> readAll(){
		
		final List<ClientType> clientType = typeService.readAll();
		
		CollectionModel<EntityModel<ClientType>> recentResources = CollectionModel.wrap(clientType);
		
		recentResources.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClientTypeController.class).readAll()).withRel("types"));
		
		return recentResources;
		
	}
	
	@GetMapping("/th")
	public CollectionModel<ClientTypeModel> readAllTH(){
		
		final List<ClientType> clientTypes = typeService.readAll();
		
		CollectionModel<ClientTypeModel> responceTypes= new ClientTypeModelAssembler().toCollectionModel(clientTypes);
		
		
		return responceTypes;
	}
	
	/*
	 * End HATEOS implementation
	 */
}
