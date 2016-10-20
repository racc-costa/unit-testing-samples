package br.com.racc.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.Socket;
import java.net.SocketException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.racc.testing.pojo.Client;
import br.com.racc.testing.pojo.ClientDAO;
import br.com.racc.testing.pojo.ClientService;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClientService.class)
public class ClientServiceTest {

	// Get private member
	// This example shows how to get a private member that does not have a get method.
	// Este exemplo mostra como obter um membro privado que não tem um método get.
	@Test
	public void testSerialNotNull() throws SocketException {
		ClientService service = new ClientService();
		double serial = Whitebox.getInternalState(service, "serial");

		assertNotNull(serial);
	}

	// Set private member
	// This example shows how to modify a private member that does not have a set method.
	// Este exemplo mostra como modificar um membro privado que não tem um método set.
	@Test
	public void testIsServiceOnline() throws SocketException {
		ClientService service = new ClientService();
		Socket socketMock = PowerMockito.mock(Socket.class);
		PowerMockito.when(socketMock.getKeepAlive()).thenReturn(true);
		Whitebox.setInternalState(service, Socket.class, socketMock);

		assertTrue(service.isServiceOnline());
	}

	// Force Exception
	// This example shows how to modify a private member that does not have a set method and force an Exception.
	// Este exemplo mostra como modificar um membro privado que não tem um método set e forçar uma Exception.
	@Test(expected = SocketException.class)
	public void testIsServiceOnlineException() throws SocketException {
		ClientService service = new ClientService();
		Socket socketMock = PowerMockito.mock(Socket.class);
		PowerMockito.when(socketMock.getKeepAlive()).thenThrow(SocketException.class);
		Whitebox.setInternalState(service, Socket.class, socketMock);

		service.isServiceOnline();
	}

	// Mock static method
	// This example shows how to mock a static method. Need @RunWith(PowerMockRunner.class) and @PrepareForTest(ClientService.class).
	// Este exemplo mostra como fazer mock de um método estático. Precisa de @RunWith(PowerMockRunner.class) e @PrepareForTest(ClientService.class).
	@Test
	public void testSerial() throws SocketException {
		PowerMockito.mockStatic(Math.class);
		Mockito.when(Math.random()).thenReturn(Double.valueOf(0.52));
		ClientService service = new ClientService();
		double serial = Whitebox.getInternalState(service, "serial");

		assertEquals(Double.valueOf(0.52), Double.valueOf(serial));
	}

	// Verify behavior
	// Este exemplo mostra verificar se um dado método foi invocado.
	// This example shows how to check whether a given method was invoked.
	@Test
	public void testInsert() {
		ClientService service = new ClientService();
		Client client = new Client("John");
		ClientDAO clientDAOMock = PowerMockito.mock(ClientDAO.class);
		Whitebox.setInternalState(service, ClientDAO.class, clientDAOMock);

		service.insert(client);
		Mockito.verify(clientDAOMock, Mockito.times(2)).insert(client);
	}
	
	// Partial mock
	// This example shows how partially mock a tested class.  Need @RunWith(PowerMockRunner.class) and @PrepareForTest(ClientService.class).
	// Este exemplo mostra como fazer mock de um método privado da classe sendo testada. Precisa de @RunWith(PowerMockRunner.class) e @PrepareForTest(ClientService.class).
	@Test
	public void testDelete() throws Exception {
		ClientService clientServiceSpy = PowerMockito.spy(new ClientService());
		Client client = new Client("John");
		PowerMockito.doReturn(true).when(clientServiceSpy, "validadeClientName", client.getName());
		
		ClientDAO clientDAOMock = PowerMockito.mock(ClientDAO.class);
		Whitebox.setInternalState(clientServiceSpy, ClientDAO.class, clientDAOMock);

		clientServiceSpy.delete(client);
		Mockito.verify(clientDAOMock, Mockito.times(1)).delete(client);
	}
}
