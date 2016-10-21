package br.com.racc.cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClienteDAO {
	List<Cliente> clientes = new ArrayList<Cliente>();

	public void persiste(Cliente client) {
		clientes.add(client);
	}
	
	public List<Cliente> getClientes() {
		return Collections.unmodifiableList(clientes);
	}

	public void remove(Cliente client) {
		// Não faz nada
	}
}
