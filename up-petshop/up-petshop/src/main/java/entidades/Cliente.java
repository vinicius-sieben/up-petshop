package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity // Essa notação significa que essa é uma entidade de domínio e corresponde a uma
		// tabela.
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // Essa notação corresponde a coluna que vou definir como chave primária da
		// tabela.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Essa notação representa que essa chave será gerada
														// automaticamente pelo banco de dados.
	private int id;
	@Column
	private String nome;
	private String cpf;
	private String contato;
	private String endereco;
	@OneToMany(mappedBy = "cliente")
	private List<Animal> animais = new ArrayList<Animal>();

	public Cliente() {
	}

	public Cliente(int id, String nome, String cpf, String contato, String endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.contato = contato;
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

}
