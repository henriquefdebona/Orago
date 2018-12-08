package model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import util.BancoDeDados;

@Entity
public class cadagenda implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6808600011875621219L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	@Column
    private String titulo;
	@Column
    private Date data_inicio;
	@Column
    private Date data_fim;
    @Column
    private String descricao;
    @Column
    private String dia_todo;
    
	public transient BancoDeDados connectionBD = null;

    public cadagenda() {
    }

    public cadagenda(String title, Date start, Date end) {
        this.titulo = title;
        this.data_inicio = start;
        this.data_fim = end;
    }

    public cadagenda(String title, Date start, Date end, String allDay) {
        this.titulo = title;
        this.data_inicio = start;
        this.data_fim = end;
        this.dia_todo = allDay;
    }

    public cadagenda(String title, Date start, Date end, Object data) {
        this.titulo = title;
        this.data_inicio = start;
        this.data_fim = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String title) {
        this.titulo = title;
    }

    public Date getDataInicio() {
        return data_inicio;
    }

    public void setDataInicio(Date startDate) {
        this.data_inicio = startDate;
    }

    public Date getDataFim() {
        return data_fim;
    }

    public void setDataFim(Date endDate) {
        this.data_fim = endDate;
    }

    public boolean isDiaTodo() {
    	if(dia_todo.equals("S")) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public String getDiaTodo() {
        return dia_todo;
    }
    
    public void setDiaTodo(String diaTodo) {
        this.dia_todo = diaTodo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		cadagenda other = (cadagenda) obj;
		if (dia_todo != other.dia_todo)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (data_fim == null) {
			if (other.data_fim != null)
				return false;
		} else if (!data_fim.equals(other.data_fim))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (data_inicio == null) {
			if (other.data_inicio != null)
				return false;
		} else if (!data_inicio.equals(other.data_inicio))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
    	
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (dia_todo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((data_fim == null) ? 0 : data_fim.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((data_inicio == null) ? 0 : data_inicio.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

    @Override
    public String toString() {
        return "CadAgenda{titulo=" + titulo + ",dataInicio=" + data_inicio + ",dataFim=" + data_fim + "}";
    }
    
    public void insere(cadagenda evento) throws SQLException {
		
		try {
			
			String query = "insert              " +
					       "  into cadagenda    " +
					       "       (id,         " +
					       "        titulo,     " +
					       "        data_inicio " +
					       "        data_fim    " +
					       "        descricao   " +
					       "        dia_todo)   " +
					       " values (" + evento.getId() + "," +
					                     evento.getTitulo() + "," +
					                     evento.getDataInicio() + "," +
					                     evento.getDataFim() + "," +
					                     evento.getDescricao() + "," +
					                     evento.getDiaTodo();
			
			connectionBD.setResultset(connectionBD.statement.executeQuery(query));
			connectionBD.statement = connectionBD.connection.createStatement();
			
		} finally {
			
			connectionBD.connection.close();
			
		}
    	
    }
    
	@SuppressWarnings("null")
	public void excluir(cadagenda evento) throws SQLException {
		
		BancoDeDados connectionBD = null;
		connectionBD.conectar();
		
		try {
			
			String query = "delete" +
		                   "  from cadagenda" +
					       " where id = " + evento.getId();
			connectionBD.setResultset(connectionBD.statement.executeQuery(query));
			connectionBD.statement = connectionBD.connection.createStatement();
			connectionBD.connection.commit();
			
		}catch(Exception e) {
			connectionBD.connection.rollback();
		}
		
	}
    
	public void listarAgenda() throws SQLException {
		
		connectionBD.conectar();
		
		if(connectionBD.estaConectado()) {
		
			try {
				
				String query = "select * from cadagenda";
			
				connectionBD.setResultset(connectionBD.statement.executeQuery(query));
				connectionBD.statement = connectionBD.connection.createStatement();
				
				while(connectionBD.getResultset().next()) {
					cadagenda evento = new cadagenda();
					
					evento.setId(connectionBD.resultset.getString("id"));
					evento.setTitulo(connectionBD.resultset.getString("titulo"));
					evento.setDataInicio(connectionBD.resultset.getDate("data_inicio"));
					evento.setDataFim(connectionBD.resultset.getDate("data_fim"));
					evento.setDescricao(connectionBD.resultset.getString("descricao"));
					evento.setDiaTodo(connectionBD.resultset.getString("dia_todo"));
					
				}
			}catch(Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
		} else {
			System.out.println("Não foi possível estabelecer conexão com o banco de dados!");
		}
	}
	
    public void salvar() throws SQLException {
    	connectionBD.connection.commit();
    }
    
}
