package br.com.racc.cliente;

import static org.junit.Assert.*;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

public class ClienteTest {

	// Mock do constructor
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
	
	@Test
	public void testIsNomeVazioFalse() throws Exception {
		Cliente cliente = new Cliente("Teste");
		assertFalse((Boolean) Whitebox.invokeMethod(cliente, "isNomeVazio"));
	}
}
