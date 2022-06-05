package rest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.model.Client;
import rest.model.ClientModel;
import rest.model.ClientModelAssembler;
import rest.model.ClientType;
import rest.service.ClientService;
import rest.service.ClientTypeService;



@RestController
@RequestMapping(path = "/clients", produces = "application/json")
public class ClientController {
	
	private final ClientService clientService;
	
	private final ClientTypeService typeService;
	
	@Autowired
	public ClientController(ClientService clientService, ClientTypeService typeService) {
		
		this.clientService=clientService;
		
		this.typeService=typeService;
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Client client) {
		
		List<ClientType>clientTypes=new ArrayList<>();
		
		Optional<List<ClientType>>optClientTypes=Optional.ofNullable(client.getClientsType());
		
		if(optClientTypes.isPresent()) {
			
			clientTypes=optClientTypes.get();
			
			List<ClientType>tmpList=new ArrayList<>();
			
			clientTypes.forEach((t)-> {
				
				Optional<Integer>optID=Optional.ofNullable(t.getTypeID());
				
				if(optID.isPresent()) {
					
					Optional<ClientType>optClientType=Optional.ofNullable(typeService.readOne(optID.get()));
					
					if(optClientType.isPresent()) tmpList.add(optClientType.get());
					
					//tmpList.add(typeService.readOne(optID.get()));
				
				//Integer id=t.getTypeID();
				
				//tmpList.add(typeService.readOne(id));
				}
				
			});
			
			if(tmpList.size()>0)  clientTypes=tmpList;
			
			else
				clientTypes=typeService.readAll();
			
			
		} else {
		
			clientTypes=typeService.readAll();
		}
		
		client.setClientsType(clientTypes);
		
		
		
	   clientService.create(client);
	   return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Client>> read() {
		
	   final List<Client> clients = clientService.readAll();

	   return clients != null &&  !clients.isEmpty()
	           ? new ResponseEntity<>(clients, HttpStatus.OK)
	           : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/*
	 * HATEOS implementation
	 * 
	 */
	
	@GetMapping("/clientsh")
	public CollectionModel<EntityModel<Client>> readAll(){
		
		final List<Client> clients = clientService.readAll();
		
		CollectionModel<EntityModel<Client>> recentResources = CollectionModel.wrap(clients);
		
		recentResources.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClientController.class).readAll()).withRel("clients"));
		
		return recentResources;
		
	}
	
	@GetMapping("/clh")
	public CollectionModel<ClientModel> readAllH(){
		
		final List<Client> clients = clientService.readAll();
		
		CollectionModel<ClientModel> responceClients= new ClientModelAssembler().toCollectionModel(clients);
		
		
		return responceClients;
	}
	
	/*
	 * End HATEOS implementation
	 */
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> read(@PathVariable(name = "id") int id) {
		
	   final Client client = clientService.read(id);

	   return client != null
	           ? new ResponseEntity<>(client, HttpStatus.OK)
	           : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Client client) {
	   final boolean updated = clientService.update(client, id);

	   return updated
	           ? new ResponseEntity<>(HttpStatus.OK)
	           : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
	   final boolean deleted = clientService.delete(id);

	   return deleted
	           ? new ResponseEntity<>(HttpStatus.OK)
	           : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

}
