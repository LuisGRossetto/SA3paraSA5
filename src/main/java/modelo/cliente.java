package modelo;

/**
 * Esta classe modelo (JavaBean) representa uma entidade de usuário.
 *
 */

public class cliente {
	protected int matricula;
	protected String nome;
	protected String endereco;
	protected String modalidade;

	public cliente() {
	}

	public cliente(String nome, String endereco, String modalidade) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.modalidade = modalidade;
	}

	public cliente(int matricula, String nome, String endereco, String modalidade) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.endereco = endereco;
		this.modalidade = modalidade;
	}

	public int getmatricula() {
		return matricula;
	}

	public void setmatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getendereco() {
		return endereco;
	}

	public void setendereco(String endereco) {
		this.endereco = endereco;
	}

	public String getmodalidade() {
		return modalidade;
	}

	public void setmodalidade(String modalidade) {
		this.modalidade = modalidade;
	}
}