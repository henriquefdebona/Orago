package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	@Column
	private String nome;
	@Column
	private String email;
	@Column
	private String telefone;
	@Column
	private String end_rua;
	@Column
	private String end_bairro;
	@Column
	private String end_cidade;
	@Column
	private String end_numero;
	@Column
	private String end_complemento;
	@Column
	private String end_uf;
	@Column
	private String end_cep;
	@Column
	private String cpf_cnpj;
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEnd_rua() {
		return end_rua;
	}
	public void setEnd_rua(String end_rua) {
		this.end_rua = end_rua;
	}
	public String getEnd_bairro() {
		return end_bairro;
	}
	public void setEnd_bairro(String end_bairro) {
		this.end_bairro = end_bairro;
	}
	public String getEnd_cidade() {
		return end_cidade;
	}
	public void setEnd_cidade(String end_cidade) {
		this.end_cidade = end_cidade;
	}
	public String getEnd_numero() {
		return end_numero;
	}
	public void setEnd_numero(String end_numero) {
		this.end_numero = end_numero;
	}
	public String getEnd_complemento() {
		return end_complemento;
	}
	public void setEnd_complemento(String end_complemento) {
		this.end_complemento = end_complemento;
	}
	public String getEnd_uf() {
		return end_uf;
	}
	public void setEnd_uf(String end_uf) {
		this.end_uf = end_uf;
	}
	public String getEnd_cep() {
		return end_cep;
	}
	public void setEnd_cep(String end_cep) {
		this.end_cep = end_cep;
	}
	public String getcpf_cnpj() {
		return cpf_cnpj;
	}
	public void setcpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((end_bairro == null) ? 0 : end_bairro.hashCode());
		result = prime * result + ((end_cep == null) ? 0 : end_cep.hashCode());
		result = prime * result + ((end_cidade == null) ? 0 : end_cidade.hashCode());
		result = prime * result + ((cpf_cnpj == null) ? 0 : cpf_cnpj.hashCode());
		result = prime * result + ((end_complemento == null) ? 0 : end_complemento.hashCode());
		result = prime * result + ((end_numero == null) ? 0 : end_numero.hashCode());
		result = prime * result + ((end_rua == null) ? 0 : end_rua.hashCode());
		result = prime * result + ((end_uf == null) ? 0 : end_uf.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (end_bairro == null) {
			if (other.end_bairro != null)
				return false;
		} else if (!end_bairro.equals(other.end_bairro))
			return false;
		if (end_cep == null) {
			if (other.end_cep != null)
				return false;
		} else if (!end_cep.equals(other.end_cep))
			return false;
		if (end_cidade == null) {
			if (other.end_cidade != null)
				return false;
		} else if (!end_cidade.equals(other.end_cidade))
			return false;
		if (cpf_cnpj == null) {
			if (other.cpf_cnpj != null)
				return false;
		} else if (!cpf_cnpj.equals(other.cpf_cnpj))
			return false;
		if (end_complemento == null) {
			if (other.end_complemento != null)
				return false;
		} else if (!end_complemento.equals(other.end_complemento))
			return false;
		if (end_numero == null) {
			if (other.end_numero != null)
				return false;
		} else if (!end_numero.equals(other.end_numero))
			return false;
		if (end_rua == null) {
			if (other.end_rua != null)
				return false;
		} else if (!end_rua.equals(other.end_rua))
			return false;
		if (end_uf == null) {
			if (other.end_uf != null)
				return false;
		} else if (!end_uf.equals(other.end_uf))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
}
