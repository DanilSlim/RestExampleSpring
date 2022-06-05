package rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import rest.model.ClientType;

@Service
public class ClientTypeServiceImpl implements ClientTypeService {
	
	// Хранилище 
    private static final Map<Integer, ClientType> CLIENTTYPE_REPOSITORY_MAP = new HashMap<>();
    
    private static final AtomicInteger CLIENTTYPE_ID_HOLDER = new AtomicInteger();

	@Override
	public void createType(ClientType clientType) {
		final int clientTypeId = CLIENTTYPE_ID_HOLDER.incrementAndGet();
	    clientType.setTypeID(clientTypeId);;
	    CLIENTTYPE_REPOSITORY_MAP.put(clientTypeId, clientType);

	}

	@Override
	public List<ClientType> readAll() {
		return new ArrayList<>(CLIENTTYPE_REPOSITORY_MAP.values());
	}

	@Override
	public ClientType readOne(int id) {
		return CLIENTTYPE_REPOSITORY_MAP.get(id);
	}

}
