package rest.model;



import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(value="client", collectionRelation = "clients")
public class ClientModel extends RepresentationModel<ClientModel> {
	
	
	private String name;
	private String email;
	private String phone;
	//private List<ClientType> clientTypes;
	private CollectionModel<ClientTypeModel> clientTypeModel;
	
	public ClientModel(Client client) {
		// TODO Auto-generated constructor stub
		this.name=client.getName();
		this.email=client.getEmail();
		this.phone=client.getPhone();
		//this.clientTypes=client.getClientsType();
		this.clientTypeModel= new ClientTypeModelAssembler().toCollectionModel(client.getClientsType());
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	/*public List<ClientType> getClientTypes() {
		return clientTypes;
	}*/

	public CollectionModel<ClientTypeModel> getClientTypeModel() {
		return clientTypeModel;
	}
	
	

}
