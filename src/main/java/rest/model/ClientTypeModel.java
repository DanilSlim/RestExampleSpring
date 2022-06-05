package rest.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(value = "clientType", collectionRelation = "clientTypes")
public class ClientTypeModel extends RepresentationModel<ClientTypeModel> {
	
	
	
	private String typeName;
	private String typeNum;
	
	public ClientTypeModel(ClientType clientType) {
		
		this.typeName=clientType.getTypeName();
		this.typeNum=clientType.getTypeNum();
	}

	public String getTypeName() {// поле в структуре JSON "typeName": "Boss", соответствует имени метода, если 
		                         // метод будет getClientType (), то JSON  примет вид "clientType": "Boss"
		return typeName;
	}

	public String getTypeNum() {
		return typeNum;
	}
	
	
	

}
