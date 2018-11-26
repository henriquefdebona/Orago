package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Agenda implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Agenda() {
	}
	
	public Agenda(String string, Date random, Date random2) {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer sequencial;
	
	@Column
	private Integer advogado;
	
	@Column
	private Date dataInicio;
	
	@Column
	private Date dataFim;
	
	@Column
	private String titulo;
	
	@Column
	private String descricao;
	
	@Column
	private String diaTodo;

	public String getDiaTodo() {
		return diaTodo;
	}

	public void setDiaTodo(String diaTodo) {
		this.diaTodo = diaTodo;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public Integer getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Integer advogado) {
		this.advogado = advogado;
	}

	public Date getdataInicio() {
		return dataInicio;
	}

	public void setdataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getdataFim() {
		return dataFim;
	}

	public void setdataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((advogado == null) ? 0 : advogado.hashCode());
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((diaTodo == null) ? 0 : diaTodo.hashCode());
		result = prime * result + ((sequencial == null) ? 0 : sequencial.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Agenda other = (Agenda) obj;
		if (advogado == null) {
			if (other.advogado != null)
				return false;
		} else if (!advogado.equals(other.advogado))
			return false;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (diaTodo == null) {
			if (other.diaTodo != null)
				return false;
		} else if (!diaTodo.equals(other.diaTodo))
			return false;
		if (sequencial == null) {
			if (other.sequencial != null)
				return false;
		} else if (!sequencial.equals(other.sequencial))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

}