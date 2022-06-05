package rest.model;

import java.util.List;

public class Client {
	
	private Integer id;
	private String name;
	private String email;
	private String phone;
	
	private List<ClientType> clientsType;
	
	
	public Integer getId() {
		return id;
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
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<ClientType> getClientsType() {
		return clientsType;
	}
	public void setClientsType(List<ClientType> clientsType) {
		this.clientsType = clientsType;
	}
	
	
	

}
