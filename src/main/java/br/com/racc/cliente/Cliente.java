package br.com.racc.cliente;

public class Cliente {

	private Long id;
	private String nome;

	public Cliente() {
		isNomeVazio();
		System.loadLibrary("foo.dll");
	}

	public Cliente(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		if (nome == null) {
			return "Sem nome.";
		}

		return nome;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	private boolean isNomeVazio() {
		return this.nome == null;
	}
}
