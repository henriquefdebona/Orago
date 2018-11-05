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
import model.Cliente;
import util.HibernateUtil;

@ManagedBean(name = "ClienteController")
//@ManagedBean
@ViewScoped

public class ClienteController implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Cliente> listacli ;//= new ArrayList<>();
	
	private Session sessao;
	
	Cliente cli;

	public Cliente getCli() {
		return cli;
	}

	public void setCli(Cliente cli) {
		this.cli = cli;
	}

	public List<Cliente> getListacli() {
		return listacli;
	}

	public void setListacli(List<Cliente> listacli) {
		this.listacli = listacli;
	}

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	public Cliente getcli() {
		return cli;
	}

	public void setcli(Cliente cli) {
		this.cli = cli;
	}
	
	@PostConstruct
	public void instanciacli() { //construtor que é chamado sempre que inicia tela disciplina
		cli = new Cliente();
		listarClientes();
		//listaDisciplinas();
	}
	
	@SuppressWarnings("unchecked")
	public void listarClientes() {
		Session sessaao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessaao.createCriteria(Cliente.class);
			listacli = consulta.list();
			System.out.println(listacli);
		} catch (Exception e) {
			throw(e);
		}finally {
			sessaao.close();
		}
	}
	
	public void edita(ActionEvent evt) {
		cli = (Cliente)evt.getComponent().getAttributes().get("ClienteEdita");
	}
	
	public void exclui() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		try {
			t = sessao.beginTransaction();
			sessao.delete(cli);
			t.commit();
			cli = new Cliente();
			listarClientes();
			addMessage("Cadastro", "Cliente excluído com sucesso!");
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
		cli = (Cliente)evt.getComponent().getAttributes().get("ClienteExcluir");
		exclui();
	}

	public void salvar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		try {
			t = sessao.beginTransaction();
			sessao.merge(cli);
			t.commit();
			cli = new Cliente();
			listarClientes();
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
