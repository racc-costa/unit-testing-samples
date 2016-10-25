package br.com.racc.cliente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import br.com.racc.cliente.Cliente;

public class ClienteTest {

	// Fazer mock de um constructor.
	@Test
	public void testCliente() throws Exception {
		Cliente clienteMock = PowerMockito.mock(Cliente.class);
		PowerMockito.whenNew(Cliente.class).withNoArguments().thenReturn(clienteMock);
		PowerMockito.when(clienteMock.getNome()).thenCallRealMethod();

		assertEquals("Sem nome.", clienteMock.getNome());
	}

	// Invocar método privado
	@Test
	public void testIsNomeVazio() throws Exception {
		Cliente cliente = new Cliente(null);
		assertTrue((Boolean) Whitebox.invokeMethod(cliente, "isNomeVazio"));
	}
}
