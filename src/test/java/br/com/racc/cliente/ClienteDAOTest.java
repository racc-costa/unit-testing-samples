package br.com.racc.cliente;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

public class ClienteDAOTest {

	@Test
	public void testPersiste() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = new Cliente("John");
		List<Cliente> clientes = PowerMockito.mock(new ArrayList<Cliente>().getClass());
		Whitebox.setInternalState(clienteDAO, "clientes", clientes);

		clienteDAO.persiste(cliente);
		Mockito.verify(clientes, Mockito.times(1)).add(cliente);
	}

	@Test
	public void testGetClientes() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = new Cliente("John");

		clienteDAO.persiste(cliente);
		Assert.assertEquals(cliente, clienteDAO.getClientes().get(0));
	}
	
	@Test
	public void testRemove() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = new Cliente("John");

		clienteDAO.persiste(cliente);
		Assert.assertEquals(cliente, clienteDAO.getClientes().get(0));
		clienteDAO.remove(cliente);
		Assert.assertEquals(0, clienteDAO.getClientes().size());
	}
}
