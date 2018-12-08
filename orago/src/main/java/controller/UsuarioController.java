package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.cadusuario;
import util.HibernateUtil;

@ManagedBean(name = "UsuarioController")
@ViewScoped

public class UsuarioController implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<cadusuario> listaUsr ;//= new ArrayList<>();
	
	private Session sessao;
	
	cadusuario usr;

	public List<cadusuario> getListaCtt() {
		return listaUsr;
	}

	public List<cadusuario> getListaUsr() {
		return listaUsr;
	}

	public void setListaUsr(List<cadusuario> listaUsr) {
		this.listaUsr = listaUsr;
	}

	public cadusuario getUsr() {
		return usr;
	}

	public void setUsr(cadusuario usr) {
		this.usr = usr;
	}

	public void setListaCtt(List<cadusuario> listaUsr) {
		this.listaUsr = listaUsr;
	}

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	public cadusuario getCtt() {
		return usr;
	}

	public void setCtt(cadusuario usr) {
		this.usr = usr;
	}
	
	
	@PostConstruct
	public void instanciaCtt() { //construtor que é chamado sempre que inicia tela disciplina
		usr = new cadusuario();
		listarContatos();
		//listaDisciplinas();
	}
	
	
	@SuppressWarnings("unchecked")
	public void listarContatos() {
		Session sessaao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessaao.createCriteria(cadusuario.class);
			listaUsr = consulta.list();
		} catch (Exception e) {
			throw(e);
		}finally {
			sessaao.close();
		}
	}
	
	
	
	
	public void edita(ActionEvent evt) {
		usr = (cadusuario)evt.getComponent().getAttributes().get("UsuarioEdita");
	}
	
	
	
	
	
	
public void exclui() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		try {
			t = sessao.beginTransaction();
			sessao.delete(usr);
			t.commit();
			usr = new cadusuario();
			listarContatos();
			addMessage("Exclusão", "Usuário deletado com sucesso!");
		} catch (Exception e) {
			if(t!=null) {
				t.rollback();
			}
			throw(e);
		}finally {
			sessao.close();
		}
	}
	

public void excluir(ActionEvent evt) {
	usr = (cadusuario)evt.getComponent().getAttributes().get("UsuarioExcluir");
	exclui();
}

	
	
public void salvar() {
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
	Transaction t = null;
	try {
		t = sessao.beginTransaction();
		sessao.merge(usr);
		t.commit();
		usr = new cadusuario();
		listarContatos();
		addMessage("Cadastro", "Usuário cadastrado com sucesso!");
	} catch (Exception e) {
		if(t!=null) {
			t.rollback();
		}
		throw(e);
	}finally {
		sessao.close();
	}
}
	
	
	
	
public void addMessage(String summary, String detail) {
	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	FacesContext.getCurrentInstance().addMessage(null, message);
}
	
}

