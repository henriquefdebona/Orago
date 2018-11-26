package controller;

import java.io.Serializable;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;

import model.Agenda;
import util.HibernateUtil;

@ManagedBean(name = "CadAgendaController")
@ViewScoped

public class CadAgendaController implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Agenda> listaAgd ;
		
	private Session sessao;
		
	Agenda agd;

	public Agenda getAgd() {
		return agd;
	}

	public void setAgd(Agenda agd) {
		this.agd = agd;
	}

	public List<Agenda> getListaAgd() {
		return listaAgd;
	}

	public void setListaAgd(List<Agenda> listaAgd) {
		this.listaAgd = listaAgd;
	}

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}
		
	@SuppressWarnings("unchecked")
	public void listarAgenda() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Agenda.class);
			listaAgd = consulta.list();
			System.out.println(listaAgd);
		} catch (Exception e) {
			throw(e);
		}finally {
			sessao.close();
		}
	}
		
	public void exclui(Agenda evento) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		try {
			t = sessao.beginTransaction();
			sessao.delete(evento);
			t.commit();
			evento = new Agenda();
			listarAgenda();
			addMessage("Exclusão", "Evento excluído com sucesso!");
		} catch (Exception e) {
			if(t!=null) {
				t.rollback();
			}
			throw(e);
		}finally {
			sessao.close();
		}
	}

	public void salvar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		try {
			t = sessao.beginTransaction();
			sessao.merge(agd);
			t.commit();
			agd = new Agenda();
			listarAgenda();
			addMessage("Cadastro", "Evento cadastrado com sucesso!");
		} catch (Exception e) {
			if(t!=null) {
				t.rollback();
			}
			throw(e);
		}finally {
			sessao.close();
		}
	}	
	
	/*Início*/
     
    private CadAgendaController modeloEventoAgenda;
 
    private Agenda evento = new Agenda();
 
    @PostConstruct
    public void instanciaAgenda() {
        
        evento = new Agenda();
    	
    	listarAgenda();
    	
    }
    
    public CadAgendaController getModeloEventoAgenda() {
		return modeloEventoAgenda;
	}

	public void setModeloEventoAgenda(CadAgendaController modeloEventoAgenda) {
		this.modeloEventoAgenda = modeloEventoAgenda;
	}

	public Agenda getEvento() {
		return evento;
	}

	public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
        
        return calendar.getTime();
    }
     
    public CadAgendaController getLazyEventModel() {
        return modeloEventoAgenda;
    }
     
    public Agenda getEvent() {
        return evento;
    }
 
    public void setEvento(Agenda evento) {
        this.evento = evento;
    }
     
    public void addEvento(Agenda evento) {
    	
        if(evento.getSequencial() == null) {
        	modeloEventoAgenda.addEvento(evento);
    	}else {
    		modeloEventoAgenda.editaEvento(evento);
    	}
        
        evento = new Agenda();
        salvar();
    }

    public void editaEvento(Agenda evento) {
        int index = -1;

        for (int i = 0; i < listaAgd.size(); i++) {
            if (listaAgd.get(i).getSequencial().equals(evento.getSequencial())) {
                index = i;

                break;
            }
        }

        if (index >= 0) {
        	listaAgd.set(index, (Agenda) listaAgd);
        }
    }
    
	public void excluirEvento(Agenda evento) {
		exclui(evento);
	}
    
    public void onEventSelect(SelectEvent selectEvent) {
        evento = (Agenda) selectEvent.getObject();
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
        evento = new Agenda();
    }
     
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento movido", "Dia:" + event.getDayDelta() + ", Minuto:" + event.getMinuteDelta());
         
        addMessage(message);
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento modificado", "Dia:" + event.getDayDelta() + ", Minuto:" + event.getMinuteDelta());
         
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	/*Fim*/
	
	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
		
}