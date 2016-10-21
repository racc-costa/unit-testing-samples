package br.com.racc.cliente;

import java.net.Socket;
import java.net.SocketException;

public class ClienteService {
	private ClienteDAO clienteDAO;
	private Socket socket = new Socket();
	@SuppressWarnings("unused")
	private double serial = Math.random();

	public boolean isServicoOnline() throws SocketException {
		return socket.getKeepAlive();
	}

	public void cadastra(Cliente cliente) {
		cliente.getNome();
		clienteDAO.persiste(cliente);
		clienteDAO.persiste(cliente);
	}

	public void remove(Cliente cliente) throws Exception {
		if (!validaNomeCliente(cliente.getNome())) {
			throw new Exception("Nada.");
		}
		
		clienteDAO.remove(cliente);
	}

	private boolean validaNomeCliente(String nome) {
		return !nome.isEmpty();
	}
}
