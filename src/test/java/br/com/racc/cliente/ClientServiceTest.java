package br.com.racc.cliente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.Socket;
import java.net.SocketException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClienteService.class)
public class ClientServiceTest {

	@Rule
	public ExpectedException exceptionEsperada = ExpectedException.none();

	// Get de membro privado
	@Test
	public void testSerialNaoNulo() throws SocketException {
		ClienteService service = new ClienteService();
		double serial = Whitebox.getInternalState(service, "serial");

		assertNotNull(serial);
	}

	// Set de membro privado
	@Test
	public void testIsServicoOnline() throws SocketException {
		ClienteService service = new ClienteService();
		Socket socketMock = PowerMockito.mock(Socket.class);
		PowerMockito.when(socketMock.getKeepAlive()).thenReturn(true);
		Whitebox.setInternalState(service, Socket.class, socketMock);

		assertTrue(service.isServicoOnline());
	}

	// Simula Exception
	@Test(expected = SocketException.class)
	public void testIsServicoOnlineException() throws SocketException {
		ClienteService service = new ClienteService();
		Socket socketMock = PowerMockito.mock(Socket.class);
		PowerMockito.when(socketMock.getKeepAlive()).thenThrow(SocketException.class);
		Whitebox.setInternalState(service, Socket.class, socketMock);

		service.isServicoOnline();
	}

	// Verifica mensagem da Exception
	@Test
	public void testIsServicoOnlineMensagemException() throws Exception {
		exceptionEsperada.expect(Exception.class);
		exceptionEsperada.expectMessage("Nada.");
		ClienteService service = new ClienteService();
		Cliente cliente = new Cliente("");
		service.remove(cliente);
	}

	// Mock de um método static
	public void testSerial() throws SocketException {
		PowerMockito.mockStatic(Math.class);
		Mockito.when(Math.random()).thenReturn(Double.valueOf(0.52));
		ClienteService service = new ClienteService();
		double serial = Whitebox.getInternalState(service, "serial");

		assertEquals(Double.valueOf(0.52), Double.valueOf(serial));
	}

	// Verifica comportamento
	@Test
	public void testCadastra() {
		ClienteService service = new ClienteService();
		Cliente clienteMock = Mockito.mock(Cliente.class);
		ClienteDAO clienteDAOMock = PowerMockito.mock(ClienteDAO.class);
		Whitebox.setInternalState(service, ClienteDAO.class, clienteDAOMock);

		service.cadastra(clienteMock);
		InOrder emOrdem = Mockito.inOrder(clienteMock, clienteDAOMock);
		emOrdem.verify(clienteMock, Mockito.times(1)).getNome();
		emOrdem.verify(clienteDAOMock, Mockito.times(2)).persiste(clienteMock);
	}

	// Mock parcial
	@Test
	public void testRemove() throws Exception {
		ClienteService clienteServiceSpy = PowerMockito.spy(new ClienteService());
		Cliente cliente = new Cliente("John");
		PowerMockito.doReturn(true).when(clienteServiceSpy, "validaNomeCliente", cliente.getNome());

		ClienteDAO clienteDAOMock = PowerMockito.mock(ClienteDAO.class);
		Whitebox.setInternalState(clienteServiceSpy, ClienteDAO.class, clienteDAOMock);

		clienteServiceSpy.remove(cliente);
		Mockito.verify(clienteDAOMock, Mockito.times(1)).remove(cliente);
	}
}
