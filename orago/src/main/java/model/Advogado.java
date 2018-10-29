package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Advogado implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	@Column
	private String cod_oab;
	@Column
	private Integer area_atuacao;
	@Column
	private Integer cod_usu;
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getCod_oab() {
		return cod_oab;
	}
	public void setCod_oab(String cod_oab) {
		this.cod_oab = cod_oab;
	}
	public Integer getArea_atuacao() {
		return area_atuacao;
	}
	public void setArea_atuacao(Integer area_atuacao) {
		this.area_atuacao = area_atuacao;
	}
	public Integer getCod_usu() {
		return cod_usu;
	}
	public void setCod_usu(Integer cod_usu) {
		this.cod_usu = cod_usu;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area_atuacao == null) ? 0 : area_atuacao.hashCode());
		result = prime * result + ((cod_oab == null) ? 0 : cod_oab.hashCode());
		result = prime * result + ((cod_usu == null) ? 0 : cod_usu.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Advogado other = (Advogado) obj;
		if (area_atuacao == null) {
			if (other.area_atuacao != null)
				return false;
		} else if (!area_atuacao.equals(other.area_atuacao))
			return false;
		if (cod_oab == null) {
			if (other.cod_oab != null)
				return false;
		} else if (!cod_oab.equals(other.cod_oab))
			return false;
		if (cod_usu == null) {
			if (other.cod_usu != null)
				return false;
		} else if (!cod_usu.equals(other.cod_usu))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}