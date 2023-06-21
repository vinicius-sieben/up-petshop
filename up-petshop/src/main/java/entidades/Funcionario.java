package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String nome;
	private String cpf;
	private double salario;
	private double comissao;
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Servico> servicos = new ArrayList<Servico>();

	public Funcionario() {
	}

	public Funcionario(int id, String nome, String cpf, double salario, double comissao) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.comissao = comissao;
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

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setAnimais(Servico objServico) {
		this.servicos.add(objServico);
	}

}
