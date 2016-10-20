package br.com.racc.client;

public class Client {

	private Long id;
	private String name;

	public Client() {
		System.loadLibrary("foo.dll");
	}

	
	public Client(String name) {
		this.name = name;
	}

	public String getName() {
		if (name == null) {
			return "No name.";
		}
		
		return name;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
}
