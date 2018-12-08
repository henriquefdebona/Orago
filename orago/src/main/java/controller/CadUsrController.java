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

@ManagedBean(name = "CadUsrController")
//@ManagedBean
@ViewScoped

public class CadUsrController implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<cadusuario> listausr ;//= new ArrayList<>();
	
	private Session sessao;
	
	cadusuario usr;

	public List<cadusuario> getListausr() {
		return listausr;
	}

	public void setListausr(List<cadusuario> listausr) {
		this.listausr = listausr;
	}

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	public cadusuario getusr() {
		return usr;
	}

	public void setusr(cadusuario usr) {
		this.usr = usr;
	}
	
	@PostConstruct
	public void instanciausr() { //construtor que é chamado sempre que inicia tela disciplina
		usr = new cadusuario();
		listarUsuarios();
		//listaDisciplinas();
	}
	
	
	@SuppressWarnings("unchecked")
	public void listarUsuarios() {
		Session sessaao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessaao.createCriteria(cadusuario.class);
			listausr = consulta.list();
		} catch (Exception e) {
			throw(e);
		}finally {
			sessaao.close();
		}
	}
	
	public void edita(ActionEvent evt) {
		usr = (cadusuario)evt.getComponent().getAttributes().get("cadusuarioEdita");
	}
	
	
	public void exclui() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		try {
			t = sessao.beginTransaction();
			sessao.delete(usr);
			t.commit();
			usr = new cadusuario();
			listarUsuarios();
			addMessage("Cadastro", "Usuario deletado com sucesso!");
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
		usr = (cadusuario)evt.getComponent().getAttributes().get("cadusuarioExcluir");
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
			listarUsuarios();
			addMessage("Cadastro", "Usuario cadastrado com sucesso!");
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