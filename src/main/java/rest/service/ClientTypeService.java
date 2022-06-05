package rest.service;

import java.util.List;

import rest.model.ClientType;

public interface ClientTypeService {
	
	void createType (ClientType clientType);
	
	List<ClientType> readAll();
	
	ClientType readOne(int id);

}
