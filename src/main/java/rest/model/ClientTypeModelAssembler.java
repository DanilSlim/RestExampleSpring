package rest.model;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;


import rest.controllers.ClientTypeController;

public class ClientTypeModelAssembler extends RepresentationModelAssemblerSupport<ClientType, ClientTypeModel> {

	public ClientTypeModelAssembler() {
		super(ClientTypeController.class, ClientTypeModel.class);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected ClientTypeModel instantiateModel(ClientType clientType) {
		
		return new ClientTypeModel(clientType);
	}

	@Override
	public ClientTypeModel toModel(ClientType clientType) {
		
		ClientTypeModel clientTypeModel=instantiateModel(clientType);
		
		clientTypeModel.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(ClientTypeController.class).read(clientType.getTypeID())).withSelfRel());
		
		return clientTypeModel;
	}
	
	@Override
	public CollectionModel<ClientTypeModel> toCollectionModel(Iterable<? extends ClientType> clientTypes) {
		
		CollectionModel<ClientTypeModel> allClientTypes=super.toCollectionModel(clientTypes);
		
		allClientTypes.add(WebMvcLinkBuilder.
				linkTo(WebMvcLinkBuilder.methodOn(ClientTypeController.class).readAllTH()).withRel("types"));
		
		return allClientTypes;
	}

}
