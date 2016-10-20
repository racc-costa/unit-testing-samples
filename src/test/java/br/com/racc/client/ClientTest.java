package br.com.racc.client;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.racc.client.Client;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Client.class)
public class ClientTest {

	// Mock constructor
	// This example shows how to mock a constructor.
	// Este exemplo mostra como fazer mock de um construtor.
	@Test
	public void testClient() throws Exception {
		Client clientMock = PowerMockito.mock(Client.class); 
		PowerMockito.whenNew(Client.class).withNoArguments().thenReturn(clientMock);
		PowerMockito.when(clientMock.getName()).thenCallRealMethod();

		assertEquals("No name.", clientMock.getName());
	}

}
