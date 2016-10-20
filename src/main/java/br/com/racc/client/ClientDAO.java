package br.com.racc.client;

import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
	List<Client> clients = new ArrayList<Client>();
	
	public void insert(Client client) {
		clients.add(client);
	}

	public void delete(Client client) {
		// do nothing
	}
}
