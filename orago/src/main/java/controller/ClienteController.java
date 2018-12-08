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
import model.cadcliente;
import util.HibernateUtil;

@ManagedBean(name = "ClienteController")
@ViewScoped

public class ClienteController implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<cadcliente> listacli ;//= new ArrayList<>();
	
	private Session sessao;
	
	cadcliente cli;

	public cadcliente getCli() {
		return cli;
	}

	public void setCli(cadcliente cli) {
		this.cli = cli;
	}

	public List<cadcliente> getListacli() {
		return listacli;
	}

	public void setListacli(List<cadcliente> listacli) {
		this.listacli = listacli;
	}

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	public cadcliente getcli() {
		return cli;
	}

	public void setcli(cadcliente cli) {
		this.cli = cli;
	}
	
	@PostConstruct
	public void instanciacli() { //construtor que é chamado sempre que inicia tela disciplina
		cli = new cadcliente();
		listarcadclientes();
		//listaDisciplinas();
	}
	
	@SuppressWarnings("unchecked")
	public void listarcadclientes() {
		Session sessaao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessaao.createCriteria(cadcliente.class);
			listacli = consulta.list();
			System.out.println(listacli);
		} catch (Exception e) {
			throw(e);
		}finally {
			sessaao.close();
		}
	}
	
	public void edita(ActionEvent evt) {
		cli = (cadcliente)evt.getComponent().getAttributes().get("cadclienteEdita");
	}
	
	public void exclui() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		try {
			t = sessao.beginTransaction();
			sessao.delete(cli);
			t.commit();
			cli = new cadcliente();
			listarcadclientes();
			addMessage("Cadastro", "cadcliente excluído com sucesso!");
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
		cli = (cadcliente)evt.getComponent().getAttributes().get("cadclienteExcluir");
		exclui();
	}

	public void salvar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		try {
			t = sessao.beginTransaction();
			sessao.merge(cli);
			t.commit();
			cli = new cadcliente();
			listarcadclientes();
			addMessage("Cadastro", "Cliente cadastrado com sucesso!");
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
