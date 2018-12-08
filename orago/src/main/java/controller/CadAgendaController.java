package controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;

import model.cadagenda;

@ManagedBean(name = "CadAgendaController")
@ViewScoped
public class CadAgendaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4375240861967728324L;
	
	private List<cadagenda> agendaList;
	
	public cadagenda agenda;
	
	public cadagenda getAgd() {
		return agenda;
	}

	public void setAgd(cadagenda agd) {
		this.agenda = agd;
	}

	public List<cadagenda> getListaAgd() {
		return agendaList;
	}

	public void setAgendaList(List<cadagenda> agendaList) {
		this.agendaList = agendaList;
	}
	
	public void instanciaAgd() throws SQLException {
		agenda = new cadagenda();
		listarAgenda();
	}
	
	public void listarAgenda() throws SQLException {
		agenda.listarAgenda();
	}
	
	public void edita(ActionEvent evt) {
		agenda = (cadagenda)evt.getComponent().getAttributes().get("cadagendaEdita");
	}
	
	public void excluir() throws SQLException{
		
		agenda.excluir(agenda);
		addMessage("Exclusão", "Evento excluído com sucesso!");
		
	}
	
	public void salvar() throws SQLException {
		agenda.salvar();
	}
	
	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
     
    public void onEventSelect(SelectEvent selectEvent) {
        agenda = (cadagenda) selectEvent.getObject();
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
    	agenda = new cadagenda("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
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
}