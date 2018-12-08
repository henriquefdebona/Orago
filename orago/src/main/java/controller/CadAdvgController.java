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
import model.cadadvogado;
import util.HibernateUtil;

@ManagedBean(name = "CadAdvgController")
//@ManagedBean
@ViewScoped

public class CadAdvgController implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<cadadvogado> listaadvg ;//= new ArrayList<>();
	
	private Session sessao;
	
	cadadvogado advg;

	public List<cadadvogado> getListaadvg() {
		return listaadvg;
	}

	public void setListaadvg(List<cadadvogado> listaadvg) {
		this.listaadvg = listaadvg;
	}

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	public cadadvogado getadvg() {
		return advg;
	}

	public void setadvg(cadadvogado advg) {
		this.advg = advg;
	}
	
	
	@PostConstruct
	public void instanciaadvg() { //construtor que é chamado sempre que inicia tela advogado
		advg = new cadadvogado();
		listarAdvogados();
		//listaDisciplinas();
	}
	
	
	@SuppressWarnings("unchecked")
	public void listarAdvogados() {
		Session sessaao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessaao.createCriteria(cadadvogado.class);
			listaadvg = consulta.list();
		} catch (Exception e) {
			throw(e);
		}finally {
			sessaao.close();
		}
	}
	
	
	
	
	public void edita(ActionEvent evt) {
		advg = (cadadvogado)evt.getComponent().getAttributes().get("AdvogadoEdita");
	}
	
public void exclui() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		try {
			t = sessao.beginTransaction();
			sessao.delete(advg);
			t.commit();
			advg = new cadadvogado();
			listarAdvogados();
			addMessage("Cadastro", "Advogado deletado com sucesso!");
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
	advg = (cadadvogado)evt.getComponent().getAttributes().get("AdvogadoExcluir");
	exclui();
}

	
	
public void salvar() {
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
	Transaction t = null;
	try {
		t = sessao.beginTransaction();
		sessao.merge(advg);
		t.commit();
		advg = new cadadvogado();
		listarAdvogados();
		addMessage("Cadastro", "Advogado cadastrado com sucesso!");
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
