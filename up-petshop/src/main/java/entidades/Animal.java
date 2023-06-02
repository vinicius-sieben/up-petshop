package entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Animal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String nome;
	private int idade;
	private String especie;
	private String cor;
	private String raca;
	private boolean castrado;
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "tutor_id")
	private Cliente cliente;

	public Animal() {
		super();
	}

	public Animal(String nome, int idade, Cliente tutor, String especie, String cor, String raca, boolean castrado) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.cliente = tutor;
		this.especie = especie;
		this.cor = cor;
		this.raca = raca;
		this.castrado = castrado;
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

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Cliente getTutor() {
		return cliente;
	}

	public void setTutor(Cliente tutor) {
		this.cliente = tutor;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public boolean isCastrado() {
		return castrado;
	}

	public void setCastrado(boolean castrado) {
		this.castrado = castrado;
	}

	public static boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
