package br.com.racc.testing.pojo;

import java.net.Socket;
import java.net.SocketException;

public class ClientService {
	private ClientDAO dao;
	private Socket socket = new Socket();
	private double serial = Math.random();

	public boolean isServiceOnline() throws SocketException {
		return socket.getKeepAlive();
	}

	public void insert(Client client) {
		dao.insert(client);
		dao.insert(client);
	}

	public void delete(Client client) {
		if (!validadeClientName(client.getName())) {
			throw new RuntimeException("");
		}
		
		dao.delete(client);
	}

	private boolean validadeClientName(String name) {
		return name.isEmpty();
	}
}
