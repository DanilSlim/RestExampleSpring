package rest.model;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import rest.controllers.ClientController;


public class ClientModelAssembler extends RepresentationModelAssemblerSupport<Client, ClientModel> {

	public ClientModelAssembler() {
		super(ClientController.class, ClientModel.class);
		
	}
	
	
	@Override
	protected ClientModel instantiateModel(Client client) {
		
		
		return new ClientModel(client);
	}

	@Override
	public ClientModel toModel(Client client) {
		
		ClientModel clientModel= instantiateModel(client);
		
		clientModel.add(WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn(ClientController.class).read(client.getId())).withSelfRel());
		
		return clientModel;
	}
	
	@Override
	public CollectionModel<ClientModel> toCollectionModel(Iterable<? extends Client> clients) {
		
		CollectionModel<ClientModel> allClients=super.toCollectionModel(clients);
		
		allClients.add(WebMvcLinkBuilder.
				linkTo(WebMvcLinkBuilder.methodOn(ClientController.class).readAllH()).withRel("clients"));
	
		return allClients;
	}

}
